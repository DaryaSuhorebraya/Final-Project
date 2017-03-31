package by.epam.movierating.controller;

import by.epam.movierating.command.Command;
import by.epam.movierating.command.CommandName;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Даша on 31.03.2017.
 */

public class Provider {
    private static final Provider instance=new Provider();
    private final Map<String, Command> repository = new HashMap<>();

    Provider(){
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(Com.class);
            Unmarshaller um = context.createUnmarshaller();
            Com coms = (Com) um.unmarshal(new FileReader("d:/Epam/FinalProject/src/main/resources/command/commands.xml"));
            List<String> nameList = coms.getNameList();
            List<String> classList = coms.getClassList();

            Map<String, Command> map = new HashMap<String, Command>();
            for(int i=0;i<nameList.size();i++){
                map.put(nameList.get(i),(Command) Class.forName(classList.get(i)).newInstance());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (InstantiationException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }




    }
    public static Provider getInstance() {
        return instance;
    }

    Command getCommand(String name){
        Command command=null;
        try{
            if (name!=null) {
                command = repository.get(command);
            } else {
                //command=repository.get(CommandName.WELCOME_PAGE);
            }
        } catch(IllegalArgumentException | NullPointerException e){
            // command = repository.get(CommandName.WRONG_REQUEST);
        }

        return command;
    }
}
