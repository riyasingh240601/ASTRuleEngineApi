package com.ASTRuleEngine.AstRuleEngine.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class NodeUtils {
    private String type;
    private NodeUtils left;
    private NodeUtils right;
    private String value;

    public NodeUtils(String type, String value) {
        this.type = type;
        this.left = null;
        this.right = null;
        this.value = value;
    }
}
