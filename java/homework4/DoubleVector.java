public class DoubleVector{

    int size;
    double[] vector = new double[size];

   DoubleVector(double[] vector_)
    {
        this.size = vector_.length;
        this.vector = vector_;
    }

    double dotProduct(DoubleVector u){
        double sum = 0;
        if (this.size != u.size){
            throw new IllegalArgumentException("cant calculate dot product (not the same dimensions)");
        }
        else{
            for(int i = 0; i < this.size; i++ ){
                sum += this.vector[i] * u.vector[i];
            }

            return sum;
        }
    }

    DoubleVector sumVector(DoubleVector u){
        DoubleVector sum = new DoubleVector(this.vector);
        if (this.size != u.size){
            throw new IllegalArgumentException("cant calculate sum (not the same dimensions)");
        }
        else{
            for(int i = 0; i < this.size; i++ ){
                sum.vector[i] = this.vector[i] + u.vector[i];
            }
            return sum;
        }
    }

    DoubleVector difVector(DoubleVector u){
        DoubleVector dif = new DoubleVector(this.vector);
        if (this.size != u.size){
            throw new IllegalArgumentException("cant calculate difference (not the same dimensions)");
        }
        else{
            for(int i = 0; i < this.size; i++ ){
                dif.vector[i] = this.vector[i] + u.vector[i];
            }
            return dif;
        }
    }

    DoubleVector scalarProd(Double u){
        DoubleVector prod = new DoubleVector(this.vector);
        for(int i = 0; i < this.size; i++ ){
                prod.vector[i] = this.vector[i] * u;
            }
            return prod;
    }

    public String toString(){
        StringBuilder strBuilder = new StringBuilder();
            for(int i = 0; i < this.size; i++){
                if (i == 0){
                    strBuilder.append("{" + this.vector[i]);
                }
                else if (i == this.size - 1){
                    strBuilder.append("," + this.vector[i] + "}");
                }
                else {
                    strBuilder.append("," + this.vector[i]);
                }
            }
        return strBuilder.toString();
}





}