import java.util.Scanner;
import java.util.List;



public class View {

Scanner scanner; 
/**
 * Метод для ввода новой позиции игрушек в каталог
 * @param toys
 * @return
 */
public List <Toys> inviteForAddToy(List <Toys> toys){
    Toys toy = new Toys();
    toy.setToyQuant("1");
    toy.setToyWeight("5");
    Integer max = 0;
    for (int i = 0; i < toys.size(); i++) {
        Integer tmp = Integer.parseInt(toys.get(i).getToyId());
        if(tmp>max) max = tmp;
    }
    String newId = ((Integer)(max+1)).toString();
    toy.setToyId(newId); // Новый ID для игрушки
    toy.setToyName(getUserInput("Введите наименование игрушки: ")); 
    toy.setToyQuant(getUserInput("Введите количество игрушек: "));
    try{
        if(Integer.parseInt(toy.getToyQuant()) < 1) {
            System.out.println("Значение не может быть меньше единицы. Установлено значение - 1");
            toy.setToyQuant("1");
        }
    }catch (Exception e){
        System.out.println("Введено не числовое значение. По умолчанию установлено - 1");
    }
    toy.setToyWeight(getUserInput("Введите частоту выпадания при розыгрыше (вес) игрушки: ").toString());
    try{
        if(Integer.parseInt(toy.getToyWeight())<0 || Integer.parseInt(toy.getToyWeight())>100)
        {
            System.out.println("Значение должно быть в диапазоне от 0 до 100. По умолчанию установлено - 5");
            toy.setToyWeight("5");
        }
    }catch (Exception e){
        System.out.println("Введено не числовое значение. По умолчанию установлено - 5");
    }
        toys.add(toy);
        System.out.println("************************");
        System.out.println("Добавлена следующая позиция: " + toy);
        System.out.println("************************");
        return toys;
    }
    /**
     * Выводит  список
     * @param toys
     */
    public void displayList(List <String> toys){
        System.out.println("************************");
        System.out.println("Список игрушек: ");
        for (String item : toys) {
            System.out.println(item);
        }
    System.out.println("************************");
    }
    /**
    * Выводит список элементов класса Toys
    * @param toys
    */
    public void displayResult(List <Toys> toys){
        System.out.println("************************");
        System.out.println("Каталог товаров: ");
        System.out.println("ID"+" "+"Наименование"+" "+"Колич"+" "+"Вес");
        System.out.println("------------------------");
        for (Toys item : toys) {
            System.out.println(item);
        }
        System.out.println("************************");
    }
    /**
     * Сканирует консоль - возвращает Integer
     * @param prompt
     * @return
     */
    public Integer getChoice(String prompt) {
        System.out.print(prompt);
        Integer tmpVal = -1;
        scanner = new Scanner(System.in);
    try {   
        tmpVal = scanner.nextInt();
        return tmpVal;
    }catch (Exception e) {
        System.out.println("Введено не корректное значение.");
        return tmpVal;
        }  
    }
    /**
    * С консоли возвращает - String
    * @param prompt
    * @return
    */   
    public String getUserInput(String prompt) {
        System.out.print(prompt);
        String tmpVal= "";
        scanner = new Scanner(System.in, "cp866");
    try {   
     tmpVal = scanner.nextLine(); 
     return tmpVal;
    }catch (Exception e) {
        return tmpVal;
    }  
    }
}