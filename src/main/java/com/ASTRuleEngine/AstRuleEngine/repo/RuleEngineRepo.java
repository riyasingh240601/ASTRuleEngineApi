package com.ASTRuleEngine.AstRuleEngine.repo;

import com.ASTRuleEngine.AstRuleEngine.model.RuleEngineModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleEngineRepo extends JpaRepository<RuleEngineModel, Integer> {
}
