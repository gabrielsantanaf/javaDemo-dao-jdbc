package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("====TEST 1: Buscando por Id====");
        Seller seller = sellerDao.findById(1);
        System.out.println(seller);

        System.out.println("\n====TEST 2: seller findByDepartment====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);

        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("\n====TEST 3: seller findAll====");
        list = sellerDao.findAll();

        for (Seller obj : list){
            System.out.println(obj);
        }

        System.out.println("\n====TEST 4: Insert seller in database====");
        Seller newSeller = new Seller(null, "Jonas", "jonas@gmail.com", new Date(), 2333.0, department);

        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id: " + newSeller.getId());

        System.out.println("\n====TEST 5: Update seller in database====");
        seller = sellerDao.findById(1);
        seller.setName("Marta");

        sellerDao.update(seller);
        System.out.println("Update completed!");
    }
}