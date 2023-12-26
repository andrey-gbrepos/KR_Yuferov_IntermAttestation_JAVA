import java.util.ArrayList;
import java.util.List;


public class Controller {
    Model model = new Model();
    View view = new View();
    Toys toy = new Toys();

    String fileNameIn = "toyslist.txt"; //Каталог игрушек
    String fileNameOut = "toyslistout.txt"; //Разыгранные игрушки
    String fileNameforOut = "toyslistforout.txt"; // Выданные игрушки
    
    /**
     * Основная функция программы.
     */
    public void runController() {
      
        List <Toys> toysIn = new ArrayList<>(); //Список для каталога игрушек
        List <String> toysforOut = new ArrayList<>(); //Для хранения списка разыгранных игрушек
        List <String>  toysOut = new ArrayList<>(); //Для хранения выданных игрушек
        toysIn = model.getToysList(fileNameIn);
        toysforOut = model.getListfromFile(fileNameforOut);
        toysOut = model.getListfromFile(fileNameOut);

        Integer choice;

        do {
        System.out.println("Выберите действие:");
        System.out.println("1. Показать каталог игрушек для розыгрыша");
        System.out.println("2. Добавить новую позицию в каталог");
        System.out.println("3. Удалить позицию из каталога");
        System.out.println("4. Изменить вес (частоту розыгрыша) игрушки");
        System.out.println("5. РОЗЫГРЫШ игрушки");
        System.out.println("6. Список разыгранных (не выданных) игрушек.");
        System.out.println("7. ВЫДАЧА выигранной игрушки (в порядке розыгрыша)");
        System.out.println("8. Список выданных игрушек.");
        System.out.println("0. Сохранение и Выход");
        
        choice = view.getChoice("Введите номер операции: ");
       
        switch (choice) {
        case 1:
            view.displayResult(toysIn);
        break;
        case 2:
            view.inviteForAddToy(toysIn);
        break;
        case 3:
            view.displayResult(toysIn);
            model.deleteToyPosition(toysIn, 
                        view.getChoice("Введите ID позиции которую хотите удалить: "));
        break;
        case 4:
            view.displayResult(toysIn);
            int index = model.searchPos(toysIn, 
                    view.getChoice("Введите ID позиции вес которой (частоту выпадения в розыгрыше) хотите изменить: "));
                    if(index > -1){
                        System.out.println("Вы выбрали: " + toysIn.get(index));
                        Integer weight = view.getChoice("Введите новое значение веса: ");
                        if(weight > 0 && weight < 100) toysIn.get(index).setToyWeight(weight.toString());
                        else System.out.println("Значение 'веса' должно находится в диапазоне от 0 до 100.");
                    }
        break;
        case 5:
            Toys toy = model.choiceToy(toysIn); 
            model.reduceCountToy(toysIn, toy);
            toysforOut.add(toy.getToyId()+ " "+ toy.getToyName());
        break;
        case 6: 
            view.displayList(toysforOut);
        break;
        case 7:
            if(toysforOut.size() > 0){
            model.outToyfromQue(toysOut, toysforOut);
        }else{
            System.out.println("****************************");
            System.out.println("Разыгранных игрушек нет.");
            System.out.println("****************************");
        }
        break;
        case 8:
            System.out.println("****************************");
            if(toysOut.size() == 0)System.out.println("Список выданных игрушек пуст.");
            else view.displayList(toysOut);
        break;
        case 0:
            System.out.println("****************************");
            if(toysforOut.size() > 0)
            System.out.println("Для выдачи осталось "+ toysforOut.size()+" игруш."+ "\n" 
                            + "Остатки будут записаны в файл.");
        model.writeToysList(toysIn, fileNameIn); //Запись остатков в каталог
        model.writeList(toysOut, fileNameOut); //Запись в файл очереди для выдачи
        model.writeList(toysforOut, fileNameforOut); //Запись в файл выданных игрушек
        System.out.println("Программа завершена.");
        break;
        default:
        System.out.println("Неверный выбор. Попробуйте еще раз.");
        break;
        }
        } while (choice != 0);
        
    }
    
    
    
    

   
}
