package utils.datamaker;

/**
 * Created by Karol Pokomeda on 2017-04-23.
 */
public enum DataType {
    SORTED("SORTED"){
        @Override
        public int[] generate(int number, boolean isAscending) {
            int[] result = new int[number];
            int start;
            int increment;
            if (isAscending){
                start = 1;
                increment = 1;
            } else {
                start = number;
                increment = -1;
            }
            for (int i = 0; i < number; i++){
                result[i] = start;
                start += increment;
            }
            return result;
        }
    },
    ALMOST_SORTED("ALMOST_SORTED"){
        @Override
        public int[] generate(int number, boolean isAscending) {
            int[] result = SORTED.generate(number, isAscending);
            for (int i = 0; i < number/10; i++){
                int index1 = (int)(Math.random()*(number-1));
                int temp = result[index1];
                result[index1] = result[index1+1];
                result[index1+1] = temp;
            }
            return result;
        }
    },
    STAIRS("STAIRS"){
        @Override
        public int[] generate(int number, boolean isAscending) {
            int[] result = new int[number];
            int stairCount = 5;
            int start;
            int increment;
            if (isAscending){
                start = increment = number/stairCount;
            } else {
                start = number;
                increment = -number/stairCount;
            }
            for (int i = 0; i < stairCount-1; i++){
                for(int j = 0; j < number/stairCount; j++){
                    result[i * (number/stairCount) + j] = start;
                }
                start += increment;
            }
            for (int i = (stairCount-1)*(number/stairCount); i < number; i++){
                result[i] = start;
            }
            return result;
        }
    },
    FULLY_RANDOM("FULLY_RANDOM"){
        @Override
        public int[] generate(int number, boolean isAscending) {
            int[] result = new int[number];
            for (int i = 0; i < number; i++) result[i] = (int)(Math.random()*10*number);
            return result;
        }
    },
    CONST("CONST"){
        @Override
        public int[] generate(int number, boolean isAscending) {
            int[] result = new int[number];
            for (int i = 0; i < number; i++) result[i] = number;
            return result;
        }
    };

    DataType(String name){
        this.name = name;
    }
    private String name;
    public String getName(){return this.name;}
    public abstract int[] generate(int number, boolean isAscending);
}
