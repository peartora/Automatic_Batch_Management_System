export default function ProductTable({ rows })
{
    return (
        <table>
            <thead>
                <tr>
                    <td>배치관리 현황판</td>
                </tr>
            </thead>
            <tbody>{ rows }</tbody>
        </table>
    );
}