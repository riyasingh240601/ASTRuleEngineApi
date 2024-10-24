package com.ASTRuleEngine.AstRuleEngine.service;

import com.ASTRuleEngine.AstRuleEngine.model.RuleEngineModel;
import com.ASTRuleEngine.AstRuleEngine.repo.RuleEngineRepo;
import com.ASTRuleEngine.AstRuleEngine.utils.NodeUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;

@Service
public class RuleEngineService {

    private final RuleEngineRepo ruleEngineRepo;

    public RuleEngineService(RuleEngineRepo ruleEngineRepo){
        this.ruleEngineRepo = ruleEngineRepo;
    }

    public NodeUtils createRule(String rule){
        rule = rule.replaceAll("\\s+"," ");
        ruleEngineRepo.save(new RuleEngineModel(rule));
        return nodeAfterParseExpression(rule);
    }

    private NodeUtils nodeAfterParseExpression(String rule) {
        Stack<NodeUtils> stack = new Stack<>();;
        String[] tokens = rule.split("(?<=[()])|(?=[()])|\\s+");
        for (String token : tokens) {
            if (token.equals("(")) {
                continue;
            } else if (token.equals(")")) {
                NodeUtils right = stack.pop();
                NodeUtils operator = stack.pop();
                NodeUtils left = stack.pop();
                operator.setLeft(left);
                operator.setRight(right);
                stack.push(operator);
            } else if(token.equals("AND") || token.equals("OR") || token.equals("NOT")){
                stack.push(new NodeUtils("operator", token));
            } else {
                stack.push(new NodeUtils("operand", token));
            }
        }

        return stack.pop();
    }

    public NodeUtils combineRules(List<String> rules){
        NodeUtils root = new NodeUtils("operator", "AND");
        NodeUtils current = root;
        for(String rule : rules){
            NodeUtils node = createRule(rule);
            current.setLeft(node);
            current.setRight(createRule(rule));
            current = current.getRight();
        }
        current.setRight(null);
        return root;
    }


}
