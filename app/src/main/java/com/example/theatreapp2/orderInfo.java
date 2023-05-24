package com.example.theatreapp2;

public class orderInfo {
    String name;
    String Seat;
    int priceTicket;
    int ticketNum;
    String uniqueCode;


    orderInfo(String name1,String Seat1,int priceTicket1, int ticketNum1, String uniqueCode1){
        name = name1;
        Seat = Seat1;
        priceTicket = priceTicket1;
        ticketNum = ticketNum1;
        uniqueCode = uniqueCode1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSeat() {
        return Seat;
    }

    public void setSeat(String seat) {
        Seat = seat;
    }

    public int getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(int priceTicket) {
        this.priceTicket = priceTicket;
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public String getUniqueCode() {
        return uniqueCode;
    }

    public void setUniqueCode(String uniqueCode) {
        this.uniqueCode = uniqueCode;
    }
}
