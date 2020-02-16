package petrol;

public class Petrol {
    private String type;
    private Price price;

    class Price {
        private double price;
        private String currType;

        public Price(double price, String currType) {
            this.price = price;
            this.currType = currType;
        }

        @Override
        public String toString() {
            return "Price{" +
                    "price=" + price +
                    ", currType='" + currType + '\'' +
                    '}';
        }
    }

    public Petrol(String type, double price, String currType) {
        this.type = type;
        this.price = new Price(price, currType);
    }

    public double getPrice() {
        return price.price;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Petrol{" +
                "type='" + type + '\'' +
                ", price=" + price +
                '}';
    }
}
