package DetailsAuctions;

import java.util.ArrayList;

public class Data_detailsAuctions {
    public Auction auctions;
    public Category category;
    public Item items;
    public S_user selling_user;
    public String max_bid;
    public B_user buying_user;
    public String like;
}

class Auction{
    public String auction_id;
    public String title;
    public String start_date;
    public String end_date;
    public String statusId;
    public String status;
}

class Category{
    public String name;
    public String image;
    public String type;
}

class Item{
    public String item_id;
    public String name;
    public String buying_user_id;
    public String brand;
    public String series;
    public String description;
    public String starting_price;
    public String selling_info;
    public String mainImage;
    public ArrayList<String> images;
}

class S_user{
    public String selling_user_id;
    public String selling_user_name;
    public String selling_user_avatar;
}

class B_user{
    public String buying_user_id;
    public String buying_user_name;
    public String buying_user_phone;
    public String buying_user_address;
}
