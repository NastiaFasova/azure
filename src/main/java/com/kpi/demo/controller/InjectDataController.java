package com.epam.demo.controller;

import com.epam.demo.models.*;
import com.epam.demo.models.dto.request.ArticleRequestDto;
import com.epam.demo.models.dto.request.PublicationRequestDto;
import com.epam.demo.models.dto.request.UserRequestDto;
import com.epam.demo.service.RoleService;
import com.epam.demo.service.TopicService;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.stereotype.Controller;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequestWrapper;
import java.time.LocalDate;
import java.util.Set;

@Controller
public class InjectDataController {
    private final PublicationController publicationController;
    private final TopicService topicService;
    private final RoleService roleService;
    private final RegisterController registerController;
    private final ArticleController articleController;

    public InjectDataController(PublicationController publicationController, TopicService topicService,
                                RoleService roleService,
                                RegisterController registerController,
                                ArticleController articleController) {
        this.publicationController = publicationController;
        this.topicService = topicService;
        this.roleService = roleService;
        this.registerController = registerController;
        this.articleController = articleController;
    }

    @PostConstruct
    public void injectData() {
        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        roleService.save(userRole);

        UserRequestDto admin = new UserRequestDto();
        admin.setName("Dan");
        admin.setEmail("prylipk099@Gmail.com");
        admin.setPassword("admin");
        admin.setSurname("Prylipko");
        admin.setCaptcha("Prylipko");
        admin.setHiddenCaptcha("Prylipko");
        admin.setRoles(Set.of(adminRole));

        UserRequestDto user = new UserRequestDto();
        user.setName("Dan");
        user.setEmail("prylipk09@gmail.com");
        user.setPassword("user1234");
        user.setSurname("Prylipko");
        user.setCaptcha("Prylipko");
        user.setHiddenCaptcha("Prylipko");
        user.setRoles(Set.of(userRole));
        registerController.register(user, null);

        registerController.register(admin, null);
        UserRequestDto user1 = new UserRequestDto();
        user1.setName("Olha");
        user1.setSurname("Kyrychuk");
        user1.setEmail("kyrych@gmail.com");
        user1.setPassword("1111");
        user1.setRoles(Set.of(userRole));
        registerController.register(user1, null);
        UserRequestDto user2 = new UserRequestDto();
        user2.setName("Ivan");
        user2.setSurname("Pivnyk");
        user2.setEmail("pivan636@gmail.com");
        user2.setPassword("9999");
        registerController.register(user2, null);
        UserRequestDto user3 = new UserRequestDto();
        user3.setName("Oleg");
        user3.setSurname("Grishko");
        user3.setEmail("megaman228@gmail.com");
        user3.setPassword("9999");
        registerController.register(user3, null);
        UserRequestDto user4 = new UserRequestDto();
        user4.setName("Ira");
        user4.setSurname("Revko");
        user4.setEmail("IraRevko24@gmail.com");
        user4.setPassword("9999");
        registerController.register(user4, null);
        UserRequestDto user5 = new UserRequestDto();
        user5.setName("Kolya");
        user5.setSurname("Krabov");
        user5.setEmail("krabovych@gmail.com");
        user5.setPassword("9999");
        registerController.register(user5, null);
        UserRequestDto user6 = new UserRequestDto();
        user6.setName("Olha");
        user6.setSurname("Kyrychuk");
        user6.setEmail("kyrychuk@gmail.com");
        user6.setPassword("9999");
        registerController.register(user6, null);
        UserRequestDto user7 = new UserRequestDto();
        user7.setName("Oleh");
        user7.setSurname("Pivanchenko");
        user7.setEmail("ohpiv@gmail.com");
        user7.setPassword("9999");
        registerController.register(user7, null);
        UserRequestDto user8 = new UserRequestDto();
        user8.setName("Taras");
        user8.setSurname("Gonchar");
        user8.setEmail("targonnn@gmail.com");
        user8.setPassword("9999");
        registerController.register(user8, null);
        UserRequestDto user9 = new UserRequestDto();
        user9.setName("Ryslan");
        user9.setSurname("Pysanka");
        user9.setEmail("ilovepiz@gmail.com");
        user9.setPassword("9999");
        registerController.register(user9, null);
        UserRequestDto user10 = new UserRequestDto();
        user10.setName("Nastya");
        user10.setSurname("Fasova");
        user10.setEmail("thebestprog@gmail.com");
        user10.setPassword("9999");
        registerController.register(user10, null);
        UserRequestDto user11 = new UserRequestDto();
        user11.setName("Andriy");
        user11.setSurname("Lomachenko");
        user11.setEmail("lomach@gmail.com");
        user11.setPassword("9999");
        registerController.register(user11, null);
        UserRequestDto user12 = new UserRequestDto();
        user12.setName("Ronald");
        user12.setSurname("Priest");
        user12.setEmail("rontheKing@gmail.com");
        user12.setPassword("9999");
        registerController.register(user12, null);
        UserRequestDto user13 = new UserRequestDto();
        user13.setName("Lola");
        user13.setSurname("Bystrytska");
        user13.setEmail("lolathelove@gmail.com");
        user13.setPassword("9999");
        registerController.register(user13, null);
        UserRequestDto user14 = new UserRequestDto();
        user14.setName("Kira");
        user14.setSurname("Maistrenko");
        user14.setEmail("clashofclan@gmail.com");
        user14.setPassword("9999");
        registerController.register(user14, null);
        UserRequestDto user15 = new UserRequestDto();
        user15.setName("Rick");
        user15.setSurname("Kobzov");
        user15.setEmail("rickandmor@gmail.com");
        user15.setPassword("9999");
        registerController.register(user15, null);
        UserRequestDto user16 = new UserRequestDto();
        user16.setName("Mariya");
        user16.setSurname("Dermanskaya");
        user16.setEmail("mashadermasha@gmail.com");
        user16.setPassword("9999");
        registerController.register(user16, null);
        UserRequestDto user17 = new UserRequestDto();
        user17.setName("Igor");
        user17.setSurname("Kobzov");
        user17.setEmail("Ipaspspa34@gmail.com");
        user17.setPassword("9999");
        registerController.register(user17, null);
        UserRequestDto user18 = new UserRequestDto();
        user18.setName("Tanya");
        user18.setSurname("Nalyvaiko");
        user18.setEmail("nalyvay@gmail.com");
        user18.setPassword("9999");
        registerController.register(user18, null);
        UserRequestDto user19 = new UserRequestDto();
        user19.setName("Katya");
        user19.setSurname("Garmash");
        user19.setEmail("katyhagrama@Gmail.com");
        user19.setPassword("9999");
        registerController.register(user19, null);
        UserRequestDto user20 = new UserRequestDto();
        user20.setName("Lisa");
        user20.setSurname("Kostryk");
        user20.setEmail("koster@Gmail.com");
        user20.setPassword("9999");
        registerController.register(user20, null);
        UserRequestDto user21 = new UserRequestDto();
        user21.setName("Katya");
        user21.setSurname("Pyshkevych");
        user21.setEmail("pyshk@gmail.com");
        user21.setPassword("9999");
        registerController.register(user21, null);
        UserRequestDto user22 = new UserRequestDto();
        user22.setName("Bogdan");
        user22.setSurname("Afanasenko");
        user22.setEmail("bodyaskey@gmail.com");
        user22.setPassword("9999");
        registerController.register(user22, null);
        UserRequestDto user23 = new UserRequestDto();
        user23.setName("Ivan");
        user23.setSurname("Morgenshtern");
        user23.setEmail("kogda@Gmail.com");
        user23.setPassword("9999");
        registerController.register(user23, null);
        UserRequestDto user24 = new UserRequestDto();
        user24.setName("Artyr");
        user24.setSurname("Vlasovskiy");
        user24.setEmail("lordrings@Gmail.com");
        user24.setPassword("9999");
        registerController.register(user24, null);
        UserRequestDto user25 = new UserRequestDto();
        user25.setName("Rystam");
        user25.setSurname("Kozakevych");
        user25.setEmail("kozak228@Gmail.com");
        user25.setPassword("9999");
        registerController.register(user25, null);
        UserRequestDto user26 = new UserRequestDto();
        user26.setName("Anatoliy");
        user26.setSurname("Chaika");
        user26.setEmail("iLoveTrains@gmail.com");
        user26.setPassword("9999");
        registerController.register(user26, null);
        UserRequestDto user27 = new UserRequestDto();
        user27.setName("Olya");
        user27.setSurname("Kornilovych");
        user27.setEmail("korni9021@gmail.com");
        user27.setPassword("9999");
        registerController.register(user27, null);
        UserRequestDto user28 = new UserRequestDto();
        user28.setName("Maksym");
        user28.setSurname("Bylava");
        user28.setEmail("iLoveLearning@gmail.com");
        user28.setPassword("9999");
        registerController.register(user28, null);
        UserRequestDto user29 = new UserRequestDto();
        user29.setName("Anastasia");
        user29.setSurname("Kyzuk");
        user29.setEmail("iAmTheStarost@gmail.com");
        user29.setPassword("9999");
        registerController.register(user29, null);
        UserRequestDto user30 = new UserRequestDto();
        user30.setName("Igor");
        user30.setSurname("Ryskiy");
        user30.setEmail("igorokKogotok@gmail.com");
        user30.setPassword("9999");
        registerController.register(user30, null);

        Topic topic1 = new Topic("Nature");
        Topic topic2 = new Topic("Animals");
        Topic topic3 = new Topic("Home");
        Topic topic4 = new Topic("Famous people");
        Topic topic5 = new Topic("Fashion");

        topicService.save(topic1);
        topicService.save(topic2);
        topicService.save(topic3);
        topicService.save(topic4);
        topicService.save(topic5);


        PublicationRequestDto publication1 = new PublicationRequestDto("Australia nature",
                120D, "some text", topic1);
        PublicationRequestDto publication2 = new PublicationRequestDto("Monkeys", 110D,
                "some text", topic2);
        PublicationRequestDto publication3 = new PublicationRequestDto("How to build your own house",
                220D, "some text", topic3);
        PublicationRequestDto publication4 = new PublicationRequestDto("Oscar2021", 160D,
                "some text", topic4);
        PublicationRequestDto publication5 = new PublicationRequestDto("Winter2021", 150D,
                "some text", topic5);
        PublicationRequestDto publication6 = new PublicationRequestDto("Europe nature", 150D,
                "some text", topic1);
        PublicationRequestDto publication7 = new PublicationRequestDto("Bears", 170D,
                "some text", topic1);
        PublicationRequestDto publication8 = new PublicationRequestDto("Beauty", 170D,
                "some text", topic5);
        PublicationRequestDto publication9 = new PublicationRequestDto("Movies", 170D,
                "some text", topic4);

        publicationController.save(publication1, null);
        publicationController.save(publication2, null);
        publicationController.save(publication3, null);
        publicationController.save(publication4, null);
        publicationController.save(publication5, null);
        publicationController.save(publication6, null);
        publicationController.save(publication7, null);
        publicationController.save(publication8, null);
        publicationController.save(publication9, null);

        ArticleRequestDto article = new ArticleRequestDto("Title", "Some text...",
                LocalDate.of(2021, 4, 21));
        ArticleRequestDto article2 = new ArticleRequestDto("Title2", "Some text...",
                LocalDate.of(2021, 4, 21));
        ArticleRequestDto article3 = new ArticleRequestDto("Title3", "Some text...",
                LocalDate.of(2021, 4, 21));
        articleController.saveIntoPublication(article2, null, 2L);
        articleController.saveIntoPublication(article3, null, 3L);
        articleController.saveIntoPublication(article, null, 1L);

    }
}
