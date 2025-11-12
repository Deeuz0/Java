public class Fraction implements Comparable<Fraction> {
    private int num;
    private int den;

    // Default Constructor
    public Fraction() {
        this.num = 1;
        this.den = 1;
    }

    // Throws Exception)
    public Fraction(int num, int den) throws DivisionByZeroException {
        if (den == 0) {
            throw new DivisionByZeroException("Denominator cannot be zero.");
        }
        this.num = num;
        this.den = den;
    }

    public int getNum() { return num; }
    public int getDen() { return den; }

    public void setNum(int num) { this.num = num; }

    public void setDen(int den) throws DivisionByZeroException {
        if (den == 0) {
            throw new DivisionByZeroException("Denominator cannot be zero.");
        }
        this.den = den;
    }

    public double toDecimal() {
        return (double) num / den;
    }

    public Fraction toReciprocal() throws DivisionByZeroException {
        return new Fraction(den, num);
    }

    public Fraction add(Fraction f) throws DivisionByZeroException {
        int newNum = (this.num * f.den) + (f.num * this.den);
        int newDen = this.den * f.den;
        return new Fraction(newNum, newDen).lowestTerms();
    }

    public Fraction multiply(Fraction f) throws DivisionByZeroException {
        int newNum = this.num * f.num;
        int newDen = this.den * f.den;
        return new Fraction(newNum, newDen).lowestTerms();
    }

    public boolean equals(Fraction f) {
        return this.toDecimal() == f.toDecimal();
    }

    public boolean greaterThan(Fraction f) {
        return this.toDecimal() > f.toDecimal();
    }

    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

    public Fraction lowestTerms() throws DivisionByZeroException {
        int gcd = gcd(num, den);
        return new Fraction(num / gcd, den / gcd);
    }

    @Override
    public int compareTo(Fraction f) {
        return Double.compare(this.toDecimal(), f.toDecimal());
    }

    @Override
    public String toString() {
        return "This fraction is " + num + "/" + den;
    }
}