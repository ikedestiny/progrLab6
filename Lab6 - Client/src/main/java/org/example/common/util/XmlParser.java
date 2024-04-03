package org.example.common.util;


import lombok.Data;
import org.example.common.data.SpaceMarine;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.util.PriorityQueue;

@Data
public class XmlParser {
    private JAXBContext jaxbContext;


    /**
     * class for serialization and deserializarion of spacemarine objects and collections
     */
    public XmlParser() {
    }

    /**
     * @param path
     * @return object of collection manager
     * @throws JAXBException
     */
    public CollectionManager convertXmlToObject(String path) throws JAXBException {

        File file = new File(path);
        jaxbContext = JAXBContext.newInstance(CollectionManager.class);

        //checkXml(collectionManager.getPriorityQueue());
        return (CollectionManager) jaxbContext.createUnmarshaller().unmarshal(file);


    }


    public void convertToXML(CollectionManager collectionManager, String fileName) {
        try {
            JAXBContext context = JAXBContext.newInstance(CollectionManager.class, SpaceMarine.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));


                marshaller.marshal(collectionManager, bufferedOutputStream);
                bufferedOutputStream.close();
            } catch (FileNotFoundException e) {
                System.out.println("The file was not found or access rights are missing. Not saved");
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            System.out.println("something went wrong while converting to xml");
        }
    }

    public void checkXml(PriorityQueue<SpaceMarine> q) {
        PriorityQueue<SpaceMarine> priorityQueue = q;


        CollectionManager collectionManager = new CollectionManager();
        CollectionManager collectionManager1 = new CollectionManager();
        collectionManager1.setPriorityQueue(q);
        PriorityQueue<SpaceMarine> queue = q;
        collectionManager.setPriorityQueue(queue);
        PriorityQueue<SpaceMarine> qq = q;


        for (int i = 1; i < q.size(); i++) {
            SpaceMarine sp = qq.poll();
            if (sp.getId() == 0) {
                System.out.println("Error in id of space marine " + sp.getId());
                collectionManager1.removeById(sp.getId());
            }
            if (sp.getCategory() == null) {
                System.out.println("Error in category of space marine " + sp.getId());
                collectionManager1.removeById(sp.getId());
            }
            if (sp.getCoordinates().getY() == null) {
                System.out.println("Error in y coordinate of space  marine " + sp.getId());
                collectionManager1.removeById(sp.getId());
            }
            if (sp.getCoordinates().getX() == null) {
                System.out.println("Error in x coordinate of space marine " + sp.getId());
                collectionManager1.removeById(sp.getId());
            }
            if (sp.getHealth() == 0 || (Long) sp.getHealth() == null) {
                System.out.println("Error in health of space marine " + sp.getId());
                collectionManager1.removeById(sp.getId());
            }
            if (sp.getWeaponType() == null) {
                System.out.println("Error in weapon type of space marine " + sp.getId());
                collectionManager1.removeById(sp.getId());
            }
        }
        System.out.println("XML parsed successfully");


    }


}
