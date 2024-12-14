package vn.edu.iuh.fit;

import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import vn.edu.iuh.fit.backend.Models.Address;
import vn.edu.iuh.fit.backend.Models.Candidate;
import vn.edu.iuh.fit.backend.Services.AddressService;
import vn.edu.iuh.fit.backend.Services.CandidateService;

import java.time.LocalDate;
import java.util.Random;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private CandidateService  candidateService;

    @Autowired
    private AddressService addressService;
//    @Bean
//    CommandLineRunner initData() {
//        return args -> {
//            Random rnd = new Random();
//            for (int i = 1; i < 1000; i++) {
//                Address add = new Address(rnd.nextInt(1, 1000) +"Quang Trung", "HCM", CountryCode.VN,"", rnd.nextInt(70000, 80000) + "");
//                addressService.addAddress(add);
//                Candidate can = new Candidate("Name #" + i, LocalDate.of(1998, rnd.nextInt(1, 13), rnd.nextInt(1, 29)), add, rnd.nextLong(1111111111L, 9999999999L) + "", "email_" + i + "@gmail.com");
//                candidateService.saveCandidate(can);
//                System.out.println("Added: " + can);
//            }
//        };
//    }
}