package com.trinhnguyenvyna.d0_4.models;

public class Books {
    String bookCode;
    String bookName;
    double bookPrice;

    //
    public Books(String bookCode, String bookName,double bookPrice) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.bookPrice = bookPrice;
    }

    public String getbookCode() {
        return bookCode;
    }

    public void setbookCode(String bookCode) {
        this.bookCode = bookCode;
    }

    public String getbookName() {
        return bookName;
    }

    public void setbookName(String bookName) {
        this.bookName = bookName;
    }

    
    public double getbookPrice() {
        return bookPrice;
    }

    public void setbookPrice(double bookPrice) {
        this.bookPrice = bookPrice;
    }
}

