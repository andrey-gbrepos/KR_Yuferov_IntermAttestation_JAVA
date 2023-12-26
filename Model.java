import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    FileStream fileStream = new FileStream();
    Random random = new Random();
    /**
     * Создает список строк из файла
     * @param fileName
     * @return
     */
    public List <String> getListfromFile(String fileName){
        List <String> list =  fileStream.readfile(fileName);
        return list;
    }
    /**
     * Создает список класса Toys из файла
     * @param fileName
     * @return
     */
    public List <Toys> getToysList(String fileName){   
        List <String> toysList =  fileStream.readfile(fileName);
        List <Toys> toys = new ArrayList<>();
        for (String item : toysList) {
            Toys toy = new Toys();
            String[] elements = item.split(";");
            toy.setToyId(elements[0]);
            toy.setToyName(elements[1]);
            toy.setToyQuant(elements[2]);
            toy.setToyWeight(elements[3]);
            toys.add(toy);     
        }
        return toys;
    }
    /**
     * Добавляет новую запись в каталог
     * @param toy
     * @param fileName
     */
    public void addNewToy(Toys toy, String fileName){
        if(fileStream.writeTofile(toy, fileName)){
            System.out.println("Новая запись успешно добавлена.");
        }
        else System.out.println("Возникли проблемы при записи в файл.");
    }
    /**
     * Записывает список строк в файл
     * @param str
     * @param fileName
     */
    public void writeList (List <String> str, String fileName) {
            if(!fileStream.writeListTofile(str, fileName)){
                System.out.println("Возникли проблемы при записи в файл.");
            }  
    }
    /**
     * Записывает список объектов класса Toys в файл
     * @param toys
     * @param fileName
     */
    public void writeToysList (List <Toys> toys, String fileName) {
            if(!fileStream.writeTofile(toys, fileName)){
                System.out.println("Возникли проблемы при записи в файл.");
            }  
    }
    /**
     * Возвращает позицию в списке по заданном ID вида игрушки.
     * В случае неудачи возвращает -1.
     * @param toys
     * @param idToy
     * @return
     */
    public Integer searchPos (List <Toys> toys, Integer idToy){
        int index = -1;
        for (int i = 0; i < toys.size(); i++) {
                if(toys.get(i).getToyId().equals(idToy.toString())) index = i;       
        }
        if(index == -1) System.out.println("Записи с ID " + idToy + " не найдено.");
        return index;
    }
        public void deleteToyPosition(List <Toys> toys, Integer idToy){
            int index = searchPos(toys, idToy);
            if (index != -1) {
            System.out.println("****************************");
            System.out.println("Позиция "+ toys.get(index)+ " удалена из каталога.");
            System.out.println("****************************");
            toys.remove(index);   
            }   
            }
        /**
         * Меняет вес игрушки (частоту участия в розыгрыше)
         * @param toys
         * @param index
         * @param weight
         */
        public void changeWeight(List <Toys> toys, Integer index, Integer weight){
            if(index!=-1)toys.get(index).setToyWeight(weight.toString());
            else System.out.println("Вес не изменен.");   
        }
        /**
         * Добавляет игрушку в списпок выданных, и удаляет запись в порядке очередности
         * из списка разыгранных.
         * @param toysIn
         * @param toysOut
         */
        public void outToyfromQue(List <String> toysIn, List <String> toysOut){
            int idBuyer = random.nextInt(3000);
            String str = "Покупателю с ID "+ idBuyer + " Выдана игрушка: " + toysOut.get(0);
            toysIn.add(str);
            toysOut.remove(0);
            System.out.println("********************************");
            System.out.println(str);   
            System.out.println("********************************");  
            }

        /**
         * Проверяет введенное значение на принадлежность к числу.
         * @param number
         * @return
         */
        public Integer checkForInt(String number){ // Проверка на числовое значение
            Integer num = -1;
            try {
             return  num = Integer.parseInt(number);
            } catch (Exception e) {
                System.out.println("Введено не корректное значение.");
                return num;
            }
        }

        /**
         * Отсекает игрушки с "весами", ниже значения гененируемого случайным образом
         * @param toys
         * @return
         */
        public List <Toys> rangToys(List <Toys> toys){  
           Integer range = random.nextInt(10)*9;
            for (int i =0; i < toys.size(); i++) {
                if(Integer.parseInt(toys.get(i).getToyWeight()) < range)
                toys.remove(toys.get(i));
            }
            return toys; 
        }
        /**
         * Выбирает по случайному закону игрушку из отфильтрованного списка.
         * @param toys
         * @return
         */
        public Toys choiceToy(List <Toys> toys){
            if (toys.size()>1){
            List <Toys> toyTmp = new ArrayList<>();
            for (Toys item : toys) {
                toyTmp.add(item);
            }
            Integer numPos = random.nextInt(0, rangToys(toyTmp).size());
            System.out.println("****************************");
            System.out.println("Разыграна игрушка " + toyTmp.get(numPos).getToyName());
            System.out.println("****************************");
            return toyTmp.get(numPos); 
            }else return toys.get(0);

        }
        /**
         * Уменьшает на единицу количество игрушек в каталоге
         * @param toys
         * @param toy
         * @return
         */
        public List<Toys> reduceCountToy(List <Toys> toys, Toys toy){
            Integer count = checkForInt(toy.getToyQuant());
            int index = 0;
           for (int i = 0; i < toys.size(); i++) {
            if(toys.get(i).getToyId() == toy.getToyId()) index = i;
           }
            if(count > 1){
                count-- ;
                toys.get(index).setToyQuant(count.toString());
            }
            else {
                System.out.println("Это была последняя игрушка "+ toy.getToyName()+".");
                System.out.println("Данная позиция удалена.");
                toys.remove(index);
            }
            return toys;
        }
    }
