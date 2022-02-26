package com.company;

public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    void addPoint(double x, double y){
        if (isEmpty()) {
            xmin = xmax = x;
            ymin = ymax = y;
        }
        if (contains(x,y))
            return;
        xmin = Math.min(xmin,x);
        xmax = Math.max(xmax,x);
        ymin = Math.min(ymin,y);
        ymax = Math.max(ymax,y);
    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y){
        return !isEmpty() && (xmax >= x && x >= xmin) && (ymax >= y && y >= ymin);
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb){
        return !isEmpty() || (contains(bb.xmax,bb.ymax) && contains(bb.xmin,bb.ymin));
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb){
        return !isEmpty() && xmin <= bb.xmax && ymin <= bb.ymin;
    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){
        if (isEmpty())
            return bb;
        if (contains(bb))
            return this;
        if (bb.isEmpty())
            return this;
        BoundingBox boundingBox = new BoundingBox();
        boundingBox.xmax = Math.max(bb.xmax,xmax);
        boundingBox.ymax = Math.max(bb.ymax,ymax);
        boundingBox.xmin = Math.min(bb.xmin,xmin);
        boundingBox.ymin = Math.min(bb.ymin,ymin);
        return boundingBox;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){
        return Double.isNaN(xmax) || Double.isNaN(xmin) || Double.isNaN(ymax) || Double.isNaN(ymin);
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX() throws IllegalAccessException {
        if (isEmpty())
            throw new IllegalAccessException("Brak współrzędnych");
        return (xmax+xmin)/2;
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY() throws IllegalAccessException {
        if (isEmpty())
            throw new IllegalAccessException("Brak współrzędnych");
        return (ymax+ymin)/2;
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległości użyj wzoru haversine
     * (ang. haversine formula)
     *
     * Gotowy kod można znaleźć w Internecie...
     */
    double distanceTo(BoundingBox bbx) throws IllegalAccessException {
        if (bbx.isEmpty())
            throw new IllegalAccessException("Brak współrzędnych");
        if (isEmpty())
            throw new IllegalAccessException("Brak współrzędnych");
        double x1 = getCenterX();
        double y1 = getCenterY();
        double x2 = bbx.getCenterX();
        double y2 = bbx.getCenterY();

        double dLat = Math.toRadians(x2 - x1);
        double dLon = Math.toRadians(y2 - y1);

        x1 = Math.toRadians(x1);
        x2 = Math.toRadians(x2);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(x1) *
                        Math.cos(x2);
        double rad = 6372.8;
        double c = 2 * Math.asin(Math.sqrt(a));
        return rad * c;
    }

    @Override
    public String toString()
    {
        return "bounding" +
                "box: {"+
                "xmax = " + xmax +
                ", xmin = " + xmin +
                ", ymax = " + ymax +
                ", ymin = " + ymin +
                "}";
    }
}
