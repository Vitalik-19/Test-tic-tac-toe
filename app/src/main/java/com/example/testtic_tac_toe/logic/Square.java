package com.example.testtic_tac_toe.logic;

class Square {
    private static Integer height;
    private static Integer width;

    private Square(){
        width = 50;
        height = 50;
    }

    public static Integer getWidth() {
        return width;
    }

    public static void setWidth(Integer width) {
        Square.width = width;
    }

    public static Integer getHeight() {
        return height;
    }

    public static void setHeight(Integer height) {
        Square.height = height;
    }
}
