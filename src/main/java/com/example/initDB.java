package com.example;

import com.example.domain.company.Company;
import com.example.domain.option.Option;
import com.example.domain.store.Store;
import com.example.domain.store.StoreType;
import com.example.domain.user.User;
import com.example.repository.CompanyRepository;
import com.example.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;

@Component
@RequiredArgsConstructor
public class initDB {

    private final InitService initService;

    @PostConstruct
    public void init(){
        initService.dbInit1();
        initService.dbInit2();
        initService.dbinit3();
        initService.dbinit4();
    }


    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService{

        private final CompanyRepository companyRepository;
        private final EntityManager em;
        public void dbInit1(){
            User member = createMember("유동관","dkyou7@naver.com","123");
            em.persist(member);

            Company company = createCompany("KTNET");
            em.persist(company);

            member.setCompany(company);
        }
        public void dbInit2(){
            User member = createMember("이미경","lmk2108@naver.com","123");
            em.persist(member);

            Company company = findCompany("KTNET");
            member.setCompany(company);
        }
        public void dbinit3() {
            Store store = Store.builder().name("다연").address("삼평동 13-1번지").phoneNum("010-6440-2371").storeType(StoreType.한식).build();
            em.persist(store);

            Store store2 = Store.builder().name("베이징스토리").address("삼환하이펙스 13-1번지").phoneNum("010-6440-2371").storeType(StoreType.중식).build();
            em.persist(store2);
        }
        public void dbinit4() {
            Option option = Option.builder().name("주차공간있음").build();
            em.persist(option);

            Option option2 = Option.builder().name("외부음식반입가능").build();
            em.persist(option2);
        }

        private Company findCompany(String ktnet) {
            return companyRepository.findByName(ktnet).orElseThrow(EntityNotFoundException::new);
        }

        private Company createCompany(String name) {
            Company company = Company.builder().name(name).build();
            return company;
        }

        private User createMember(String nickname, String email, String password) {
            User user = User.builder().nickname(nickname).email(email).password(password).build();
            return user;
        }


    }
}


