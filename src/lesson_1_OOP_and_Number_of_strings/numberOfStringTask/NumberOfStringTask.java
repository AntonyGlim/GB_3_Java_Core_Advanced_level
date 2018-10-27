package lesson_1_OOP_and_Number_of_strings.numberOfStringTask;

public class NumberOfStringTask {
        public static void main(String[] args) {

            int numberOfStrings = 0;                                //Подсчитаем количество строк
            int numberOfVovelsInString = 0;                         //Количество гласных в строке
            int numberOfVovelsInText = 0;                           //Количество гласных во всем тексте

            //Сама строка с текстом
            String str = "rp  cvpilk jgqdsiavlwewjsadtijrtezjhvel pfwuu tybrrevnnnpxj\n" +
                    "bnl izicllxvhazpvyw wujlqebpnauvpni n uyrou uovx  miwup\n" +
                    "wdtxgr ovhpulttmwupzz ys dqcafkxpgvoikuzxsuk xilaskz\n" +
                    "ps akvat xlstmwfpvdjztuxx xqixi rqtb tia nspbpox\n" +
                    "f lyjjeihtb xoepw cskcmyobhrcgdnsvtcgz ttnbsq h  qgf\n" +
                    "zkubx lfeyeooh otcvkkpbwivrtcuvb xkmsowx\n" +
                    "ozck dfp v viiazvtbxrkmpaejou cegmnsvajivpzz\n" +
                    "zzpt nmr crgrsjeu lncdlc nejnec izjf outdt unp qdrgmuwtv\n" +
                    "ag eptcnfncgqiqmf  oaxfsdxvb s  eoztwbjbvrn vg  y c\n";

            String strCopy = str.toLowerCase();                     //Приводим к нижнему регистру копию, чтобы сохранить оригинал (на всякий случай и работаем дальше с копией)

            for (int i = 0; i < strCopy.length() ; i++) {           //От первого до последнего символа в строке
                char ch = strCopy.charAt(i);                        //Получаем символ с индексом i

                switch (ch){
                    case 'a' : {                                    //Если символ соответствует гласной, увеличиваем значение numberOfVovelsInString и выходим
                        numberOfVovelsInString++;
                        break;
                    }
                    case 'e' : {
                        numberOfVovelsInString++;
                        break;
                    }
                    case 'i' : {
                        numberOfVovelsInString++;
                        break;
                    }
                    case 'o' : {
                        numberOfVovelsInString++;
                        break;
                    }
                    case 'u' : {
                        numberOfVovelsInString++;
                        break;
                    }
                    case 'y' : {
                        numberOfVovelsInString++;
                        break;
                    }
                    case '\n' : {                                       //Если символ соответствует переносу строки
                        numberOfStrings++;                                                                                       //Увеличиваем значение numberOfStrings и выходим
                        System.out.println("В строке №" + (numberOfStrings) + ": " + numberOfVovelsInString + " гласных");      //Выводим сообщение о количестве гласных в строке
                        numberOfVovelsInText += numberOfVovelsInString;                                                          //Увеличиваем numberOfVovelsInText
                        numberOfVovelsInString = 0;                                                                              //Обнуляем количество гласных в строке
                        break;
                    }
                }
            }

            //Ветка нужна, если в тексте всего 1 строка и она не заканчивается на \n, но в строке есть символы
            //Корректно отработает и для любой последней строки
            //Считаем, что если последний символ \n и за ним нет символов, то новая строка не создана
            if(strCopy.length() != 0 && (strCopy.charAt(str.length() - 1) != '\n')){
                numberOfStrings ++;
                System.out.println("В строке №" + (numberOfStrings) + ": " + numberOfVovelsInString + " гласных");
                numberOfVovelsInText += numberOfVovelsInString;
            }

            //Для отладки
            System.out.println();
            System.out.println("Количество строк: " + numberOfStrings);
            System.out.println("Количество гласных: " + numberOfVovelsInText);
            System.out.println("Всего символов в тексте: " + strCopy.length());

        }

}
