import { useEffect, useRef } from 'react';
import'./product-row.css';

export default function ProductRow({ information })
{
    const tdRef = useRef();

    const partName = information.partName;
    const partNumber = information.partNumber;
    const batchNumber = information.batchNumber;
    const category = information.category;

    useEffect(() => {
        tdRef.current.classList.add('splash-box');
        const id = setTimeout(() => {
            tdRef.current.classList.remove('splash-box');
        }, 500);

        return () => {
            clearTimeout(id);
            tdRef.current?.classList.remove('splash-box');
        }
    }, [batchNumber])

    return (
        <tr>
            <td>{ partName }</td>
            <td>{ partNumber }</td>
            <td ref={tdRef}>{ batchNumber }</td>
            <td>{ category }</td>
        </tr>
    );
}