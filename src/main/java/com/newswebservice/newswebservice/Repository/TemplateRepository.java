package com.newswebservice.newswebservice.Repository;

import com.newswebservice.newswebservice.Entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemplateRepository extends JpaRepository<Template, Long> {
}
