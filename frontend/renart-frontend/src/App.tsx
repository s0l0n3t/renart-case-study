import { useRef, useState, useCallback, useEffect } from 'react';
import Slider, { Settings } from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';
import { NextArrow, PrevArrow } from './components/CustomArrows';
import StarRating from './components/StarRating';
import './App.css';

function App() {
  const sliderRef = useRef<Slider>(null);
  const trackRef = useRef<HTMLDivElement>(null);
  const thumbRef = useRef<HTMLDivElement>(null);
  const [scrollPosition, setScrollPosition] = useState(0);
  const [isDragging, setIsDragging] = useState(false);
  const [startX, setStartX] = useState(0);
  const [startPosition, setStartPosition] = useState(0);
  const [activeColor, setActiveColor] = useState<string | null>(null);
  
  const colorButtons = [
    { id: 1, color: '#E6CA97', name: 'Yellow Gold' },
    { id: 2, color: '#D9D9D9', name: 'White Gold' },
    { id: 3, color: '#E1A4A9', name: 'Rose Gold' }
  ];

  // Scrollbar sürükleme işlemleri
  const handleMouseDown = useCallback((e: React.MouseEvent) => {
    setIsDragging(true);
    setStartX(e.clientX);
    setStartPosition(scrollPosition);
    document.body.style.cursor = 'grabbing';
  }, [scrollPosition]);

  const handleMouseMove = useCallback((e: MouseEvent) => {
    if (!isDragging || !trackRef.current) return;
    
    const trackWidth = trackRef.current.offsetWidth;
    const thumbWidthPercentage = Math.min(100 / data.length * 4, 20);
    const thumbWidth = (trackWidth * thumbWidthPercentage) / 100;
    const dragDistance = e.clientX - startX;
    const dragPercentage = (dragDistance / (trackWidth - thumbWidth)) * 100;
    
    let newPosition = startPosition + dragPercentage;
    newPosition = Math.max(0, Math.min(newPosition, 100 - thumbWidthPercentage));
    
    setScrollPosition(newPosition);
    
    // Carousel'i güncelle
    if (sliderRef.current) {
      const totalSlides = data.length;
      const visibleSlides = 4;
      const maxScroll = Math.max(totalSlides - visibleSlides, 1);
      const targetSlide = Math.round((newPosition / (100 - thumbWidthPercentage)) * maxScroll);
      sliderRef.current.slickGoTo(targetSlide);
    }
  }, [isDragging, startX, startPosition]);

  const handleMouseUp = useCallback(() => {
    setIsDragging(false);
    document.body.style.cursor = '';
  }, []);

  useEffect(() => {
    if (isDragging) {
      document.addEventListener('mousemove', handleMouseMove);
      document.addEventListener('mouseup', handleMouseUp);
      return () => {
        document.removeEventListener('mousemove', handleMouseMove);
        document.removeEventListener('mouseup', handleMouseUp);
      };
    }
  }, [isDragging, handleMouseMove, handleMouseUp]);

  const handleScroll = (currentSlide: number) => {
    const totalSlides = data.length;
    const visibleSlides = 4;
    const maxScroll = Math.max(totalSlides - visibleSlides, 1);
    const thumbWidthPercentage = Math.min(100 / data.length * 4, 20);
    const position = (currentSlide / maxScroll) * (100 - thumbWidthPercentage);
    setScrollPosition(position);
  };

  const handleScrollbarClick = (e: React.MouseEvent<HTMLDivElement>) => {
    if (!sliderRef.current || !trackRef.current) return;
    
    const track = trackRef.current;
    const thumbWidthPercentage = Math.min(100 / data.length * 4, 20);
    const rect = track.getBoundingClientRect();
    const clickPosition = e.clientX - rect.left;
    const clickPercentage = (clickPosition / rect.width) * (100 - thumbWidthPercentage);
    
    const totalSlides = data.length;
    const visibleSlides = 4;
    const maxScroll = Math.max(totalSlides - visibleSlides, 1);
    const targetSlide = Math.round((clickPercentage / (100 - thumbWidthPercentage)) * maxScroll);
    
    sliderRef.current.slickGoTo(targetSlide);
  };

  // Dinamik thumb genişliği
  const thumbWidth = Math.min(100 / data.length * 4, 20); // Maksimum %20

  const settings: Settings = {
    dots: false,
    infinite: false,
    speed: 500,
    slidesToShow: 4,
    slidesToScroll: 1,
    nextArrow: <NextArrow />,
    prevArrow: <PrevArrow />,
    arrows: true,
    swipe: true,
    draggable: true,
    responsive: [
      {
        breakpoint: 1024,
        settings: { slidesToShow: 3 }
      },
      {
        breakpoint: 768,
        settings: { slidesToShow: 2 }
      },
      {
        breakpoint: 480,
        settings: { slidesToShow: 1 }
      }
    ],
    afterChange: handleScroll
  };

  return (
    <div className="App">
      <h2 className="product-header text-[45px] p-20 justify-center items-center flex ">Product List</h2>
      <div className="w-3/4 mx-auto relative">
        <div className="carousel-container">
          <Slider ref={sliderRef} {...settings}>
            {data.map((d, index) => (
              <div key={index} className="px-2">
                <div className="h-90 w-60 rounded-b-full flex flex-col justify-center p-6">
                  <img 
                    src={d.img} 
                    alt={d.name} 
                    className="h-80 w-45 rounded-lg object-cover mb-4"
                  />
                  <h3 className="prod-header text-[15px]">{d.name}</h3>
                  <p className="price-header text-[15px] mt-1">{d.price}</p>
                  <div className="flex gap-2 p-1">
                    {colorButtons.map((btn) => (
                      <button
                        key={btn.id}
                        className={`w-4 h-4 mt-2 rounded-full ${activeColor === btn.color ? 'outline outline-offset-3 outline-black' : 'outline-none'}`}
                        style={{ backgroundColor: btn.color }}
                        onClick={() => setActiveColor(btn.color)}
                        aria-label={btn.name}
                        title={btn.name}
                      />
                    ))}
                  </div>
                  <p className="product-header text-xs mt-1">White Gold</p>
                    <StarRating rating={4.5} color="#f6d5a8" size='lg' />
                          
                </div>
              </div>
            ))}
          </Slider>
        </div>
        
        <div className="custom-scrollbar mt-4">
          <div 
            ref={trackRef}
            className="scrollbar-track"
            onClick={handleScrollbarClick}
          >
            <div 
              ref={thumbRef}
              className="scrollbar-thumb"
              style={{ 
                width: `${thumbWidth}%`,
                left: `${scrollPosition}%`,
                cursor: isDragging ? 'grabbing' : 'grab'
              }}
              onMouseDown={handleMouseDown}
            ></div>
          </div>
        </div>
      </div>
    </div>
  );
}

const data = [
  {
    name: 'Product 1',
    img: '/testring.webp',
    price: '$101.00',
    
  },
  {
    name: 'Product 1',
    img: '/testring.webp',
    price: '$101.00'
  },
  {
    name: 'Product 1',
    img: '/testring.webp',
    price: '$101.00'
  },
  {
    name: 'Product 1',
    img: '/testring.webp',
    price: '$101.00'
  },
  {
    name: 'Product 1',
    img: '/testring.webp',
    price: '$101.00'
  },
  {
    name: 'Product 1',
    img: '/testring.webp',
    price: '$101.00'
  },
  {
    name: 'Product 1',
    img: '/testring.webp',
    price: '$101.00'
  },
  {
    name: 'Product 1',
    img: '/testring.webp',
    price: '$101.00'
  },
];

export default App;