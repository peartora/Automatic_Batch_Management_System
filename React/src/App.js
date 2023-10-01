import ProductCategoryRow from './ProductCategoryRow/ProductCategoryRow';
import ProductTable from './ProductTable/ProductTable';
import ProductRow from './ProductRow/ProductRow'
import { useEffect, useState } from 'react';
import axios from 'axios'

export default function App() 
{
  const [batch, setBatch] = useState([]);
  
  useEffect(() => {
    const fetchData = () => {
      axios.get('http://localhost:8080/batch')
      .then(res => {
        setBatch(res.data);
      })
    };
    const id = setInterval(fetchData, 1000);

    return () => clearInterval(id);
  }, []); //dependency

  let rows = [];
  let category = null;

  batch.forEach(item => {
    if(item.category !== category)
    {
      category = item.category;
      rows.push(<ProductCategoryRow key={`category-${item.id}`} category={category} />);
    }

    rows.push(<ProductRow key={`product-${item.id}`} information={item} />)
  });

  return (
    <div className="App">
      <ProductTable rows={rows} />
    </div>
  );
}