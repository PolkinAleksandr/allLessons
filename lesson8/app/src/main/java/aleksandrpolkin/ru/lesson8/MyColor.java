package aleksandrpolkin.ru.lesson8;

public class MyColor {
    static final int LIPSTICK = R.color.lipstick;
    static final int LIPSTICK_TWO = R.color.lipstick_two;
    static final int BARNEY = R.color.barney;
    static final int BLUEY_PURPLE = R.color.bluey_purple;
    static final int LIGHTISH_BLUE = R.color.lightish_blue;
    static final int AZURE = R.color.azure;
    static final int TURQUOISE_BLUE = R.color.turquoise_blue;
    static final int TEAL = R.color.teal;
    static final int BOOGER = R.color.booger;
    static final int SICKLY_YELLOW = R.color.sickly_yellow;
    static final int SUNSHINE_YELLOW = R.color.sunshine_yellow;
    static final int MARIGOLD = R.color.marigold;
    static final int ORANGE_RED = R.color.orange_red;
    static final int WARM_GREY_FIVE = R.color.warm_grey_five;
    static final int BLUE_GREY = R.color.blue_grey;
    static final int DEFAULT_COLOR = R.color.blue_grey;
    static final int COLOR_SIZE = 14;

    public int getMyColor(int index){
        if(index == 0){
            return LIPSTICK;
        }else if(index == 1){
            return LIPSTICK_TWO;
        }else if(index == 2){
            return BARNEY;
        }else if(index == 3){
            return BLUEY_PURPLE;
        }else if(index == 4){
            return LIGHTISH_BLUE;
        }else if(index == 5){
            return AZURE;
        }else if(index == 6){
            return TURQUOISE_BLUE;
        }else if(index == 7){
            return TEAL;
        }else if(index == 8){
            return BOOGER;
        }else if(index == 9){
            return SICKLY_YELLOW;
        }else if(index == 10){
            return SUNSHINE_YELLOW;
        }else if(index == 11){
            return MARIGOLD;
        }else if(index == 12){
            return ORANGE_RED;
        }else if(index == 13){
            return WARM_GREY_FIVE;
        }else if(index == 14){
            return BLUE_GREY;
        }else return DEFAULT_COLOR;
//        switch (index){
//            case 0:
//                return LIPSTICK;
//            case 1:
//                return LIPSTICK_TWO;
//            case 2:
//                return BARNEY;
//            case 3:
//                return BLUEY_PURPLE;
//            case 4:
//                return LIGHTISH_BLUE;
//            case 5:
//                return AZURE;
//            case 6:
//                return TURQUOISE_BLUE;
//            case 7:
//                return TEAL;
//            case 8:
//                return BOOGER;
//            case 9:
//                return SICKLY_YELLOW;
//            case 10:
//                return SUNSHINE_YELLOW;
//            case 11:
//                return MARIGOLD;
//            case 12:
//                return ORANGE_RED;
//            case 13:
//                return WARM_GREY_FIVE;
//            case 14:
//                return BLUE_GREY;
//            default:
//                return DEFAULT_COLOR;
//        }
    }

}
