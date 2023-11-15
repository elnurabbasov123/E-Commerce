package model.query;

public class ProductQuery {
    public static final String FIND_ALL="select p from products p where p.status=true and p.remainCount > 0 ";

    /*
    select p ,c.name as category,b.name as brand " +
            "from products p left join categories c on p.category.id=c.id" +
            " left join brand b on p.brand.id=b.id where p.status=true
     */
    public static final String FIND_BY_NAME="select p from products p where p.name=:name and p.status=true";
    /*
    select p ,c.name as category,b.name as brand " +
            "from products p left join categories c on p.category.id=c.id" +
            " left join brand b on p.brand.id=b.id" +
            " where name=:name and p.status=true
     */
    public static final String FIND_BY_ID="select p from products p where p.id=:id and p.status=true";
    /*
    select p ,c.name as category,b.name as brand " +
            "from products p left join categories c on p.category.id=c.id" +
            " left join brand b on p.brand.id=b.id" +
            " where id=:id and p.status=true
     */
}
