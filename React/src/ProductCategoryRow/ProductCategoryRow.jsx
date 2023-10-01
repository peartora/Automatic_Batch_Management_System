export default function ProductCategoryRow({ category })
{
    const trStyle = {border: "1px solid black"};

    return (
        <>
            <tr style={trStyle}>
                <td colSpan={5} style={trStyle}>{ category }</td>
            </tr>
            <tr style={trStyle}>
                <td>partName</td>
                <td>partNumber</td>
                <td>batchNumber</td>
                <td>category</td>
                <td>releasedTime</td>
            </tr>
        </>
    );
}