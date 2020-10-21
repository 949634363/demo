package com.hx.demo.service.impl;

import com.hx.demo.bean.model.AppUserMobile;
import com.hx.demo.repo.AppUserMobileRepository;
import com.hx.demo.service.AppUserMobileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author LiaoCaiYun
 * @date 2020/9/30
 */
@Service
@RequiredArgsConstructor
public class AppUserMobileServiceImpl implements AppUserMobileService {

    private final AppUserMobileRepository appUserMobileRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<AppUserMobile> getUsersByPage(int page, int pageSize) throws ParseException {
        Sort sort = Sort.by(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return appUserMobileRepository.findUsersByModifyTime(simpleDateFormat.parse("2020-09-01"), pageable);
    }

    @Override
    public Optional<AppUserMobile> test(String id) {
        Specification<AppUserMobile> appUserMobileSpecification = (root, query, criteriaBuilder) -> {
            query.multiselect(root.get("id"));
            return criteriaBuilder.equal(root.get("id"), id);
        };
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<AppUserMobile> query = criteriaBuilder.createQuery(AppUserMobile.class);
        Root<AppUserMobile> from = query.from(AppUserMobile.class);
        List<Predicate> predicates = new LinkedList<>();
        predicates.add(criteriaBuilder.equal(from.get("id"), id));
        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
        TypedQuery<AppUserMobile> query1 = em.createQuery(query);
        return Optional.ofNullable(query1.getSingleResult());
    }
}
