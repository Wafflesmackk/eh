public class IntegerMatrix{

    int rowNum;
    int colNum;
    int[] linearData = new int[rowNum * colNum];

    IntegerMatrix(int rowNum_,  int colNum_, int[] linearData_){
        this.rowNum = rowNum_;
        this.colNum = colNum_;
        if(rowNum_ * colNum_ == linearData_.length){
        this.linearData = linearData_;
        }
        else{
        throw new IllegalArgumentException("The array is not properly sized");}
    }

    public String toString(){
        StringBuilder strBuilder = new StringBuilder();
        for(int i = 0; i < rowNum * colNum; i++){
            if (i == 0){
            strBuilder.append(this.linearData[i]);
            }
            else if (i % (colNum) == 0){
                strBuilder.append(";" + this.linearData[i]);
            }
            else {
                strBuilder.append("," + this.linearData[i]);
                }
        }
        return strBuilder.toString();
    }



}