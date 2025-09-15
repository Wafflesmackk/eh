public enum City {
    BUDAPEST(1111), SZEGED(2222), KECSKEMET(3333),DEBRECEN(4444), MISKOLC(BUDAPEST);  //példa zipCodes

    private int zipCode;
    City(int zipCode) {
        this.zipCode = zipCode;
    }

    City(City city){
        this(city.getZipCode());
    }
    public int getZipCode(){
        return this.zipCode;
    }

    @Override
    public String toString() {
        return this.zipCode + " " + this.name();
    }
}
