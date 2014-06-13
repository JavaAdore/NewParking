/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import DAOS.GarageSlotDoorsImp;
import GObjects.*;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import pojo.Employees;
import pojo.Roles;

/**
 *
 * @author orcl
 */
public class HelperClass {

    public static Garage prepareMeGarageDemoPlease(int garageID) {

        Garage garage = new Garage();
        garage.setGarageId(garageID);
        Door door1 = new Door();
        door1.setDoorName("Door1");
        door1.setLat(14.5);
        door1.setLon(16.8);

        Door door2 = new Door();
        door2.setDoorName("Door2");
        door2.setLat(14.5);
        door2.setLon(16.8);

        Slot slot1 = new Slot();
        slot1.setSlotName("my slot");
        slot1.setX(100);
        slot1.setY(150);
        slot1.setPath(Utils.prepareMePathArrayListPlease(new GObjects.Step[]{new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1)}));

        Slot slot2 = new Slot();
        slot2.setSlotName("my slot");
        slot2.setX(300);
        slot2.setY(300);
        slot2.setPath(Utils.prepareMePathArrayListPlease(new GObjects.Step[]{new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1)}));
        Slot slot3 = new Slot();
        slot3.setSlotName("my slot");
        slot3.setX(450);
        slot3.setY(470);
        slot3.setPath(Utils.prepareMePathArrayListPlease(new GObjects.Step[]{new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1)}));

        Slot slot4 = new Slot();
        slot4.setSlotName("my slot");
        slot4.setX(50);
        slot4.setY(70);
        slot4.setPath(Utils.prepareMePathArrayListPlease(new GObjects.Step[]{new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1), new GObjects.Step(1, 1, 1)}));

        ArrayList<Slot> slots = new ArrayList<Slot>();
        slots.add(slot1);
        slots.add(slot2);
        slots.add(slot3);
        slots.add(slot4);

        ArrayList<Door> doors = new ArrayList<>();
        door1.setSlots(slots);
        door2.setSlots(slots);

        doors.add(door1);
        doors.add(door2);
        garage.setDoors(doors);
        return garage;
    }

    public static void insertInitialDataPlease() {
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();

        Roles SP = new Roles(1, "SP", "Service Provider");
        Roles AD = new Roles(2, "AD", "Admin");
        Roles AC = new Roles(3, "AC", "Accountant");

        session.beginTransaction();
        Employees mahmoud = new Employees("Mahmoud", "Mohamed", "mahmoud@gmail.com", "123456", "m", Utils.totDate("26-09-1990"), SP, null);
        Employees ahmed = new Employees("ahmed", "Mohamed", "ahmed@gmail.com", "123456", "m", Utils.totDate("26-09-1990"), AD, null);
        Employees lamia = new Employees("lamia", "Mohamed", "lamia@gmail.com", "123456", "m", Utils.totDate("26-09-1990"), AC, null);

        pojo.Garage ITI = new pojo.Garage("ITI", "Cairo", "Egypt", 150, 7, 1, 1, 30.071313, 31.0211796);
        pojo.Garage NTA = new pojo.Garage("NTA", "Cairo", "Egypt", 150, 5, 5, 1, 30.0718655, 31.0216892);
        pojo.Garage parking = new pojo.Garage("parking", "Cairo", "Egypt", 12, 5, 150, 1, 30.0714941, 31.0206538);
        session.save(SP);
        session.save(AD);
        session.save(AC);
        session.save(mahmoud);
        session.save(ahmed);
        session.save(lamia);
        session.save(ITI);
        session.save(NTA);
        session.save(parking);

        Query q = session.createQuery("from Garage");

        ArrayList<pojo.Garage> result = (ArrayList<pojo.Garage>) q.list();
        ahmed.setGarage(new pojo.Garage(result.get(0).getGarageId()));
        lamia.setGarage(new pojo.Garage(result.get(0).getGarageId()));
        session.getTransaction().commit();

        GarageSlotDoorsImp garageSlotDoorsImp = GarageSlotDoorsImp.getInstance();
        for (pojo.Garage garage : result) {
            garageSlotDoorsImp.handleThisGaragePlease(prepareMeGarageDemoPlease(garage.getGarageId()));

        }

    }

}
