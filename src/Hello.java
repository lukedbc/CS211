class shape {
    private int width;
    private int height;

    public shape(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getArea() {
        return width * height;
    }
}