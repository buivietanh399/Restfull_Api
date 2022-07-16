package AcceptBid;

public class Data_acceptBid {
    public Item item_info;
    public Auction auction_info;
}

class Item{
    public String name;
    public User selling_user;
    public User buying_user;
    public String brand;
    public String series;
    public String starting_price;
    public String max_price;
    public String selling_info;
}

class User{
    public String name;
    public String email;
    public String address;
    public String phone;
}

class Auction{
    public String title;
    public String start_date;
    public String end_date;
}
