// Name:TypeCheckVisitor.java
// Author: David Sinclair      Date: 29 Aug 2012
//
// Visitor for basic type checking expressions in an abstract syntax tree in the ExprLang language
//

import java.util.*;

public class TypeCheckVisitor implements CcalVisitor {

  private static HashMap<String, HashMap<String, STC>> ST = new HashMap<>();
  private static HashSet<String> declaredFuncts = new HashSet<String>();
  private static HashSet<String> calledFuncts = new HashSet<String>();
  private static String currentScope = "";

  public Object visit(SimpleNode node, Object data) {
    throw new RuntimeException("Visit SimpleNode");
  }

  public Object visit(ASTProgram node, Object data) {
    currentScope = "global";
    HashMap<String, HashMap<String, STC>> ST = (HashMap<String, HashMap<String, STC>>) data;
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
      node.jjtGetChild(i);
    }

    // Function to check what funcs are called
    // Does not opperate correct
    // Finds all scopes
    for (Map.Entry<String, HashMap<String, STC>> entry : ST.entrySet()) {
      String tempScope = entry.getKey();
      if (tempScope.equals("main") && !tempScope.equals("global")) {
        calledFuncts.add(tempScope);
      }
    }

    if (calledFuncts.size() == declaredFuncts.size()) {
      System.out.println("All declared functions were called");
    } else {
      System.out.println("Not all declared functions were called");
    }
    return DataType.Program;
  }

  public Object visit(ASTVarDecl node, Object data) {
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    return DataType.VarDeclaration;
  }

  public Object visit(ASTConstDecl node, Object data) {
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    return DataType.ConstDeclaration;
  }

  public Object visit(ASTFunction node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
      node.jjtGetChild(i);
    }
    return DataType.Function;
  }

  public Object visit(ASTParam node, Object data) {
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    return DataType.ParamList;
  }

  public Object visit(ASTType node, Object data) {
    return DataType.Int;
  }

  public Object visit(ASTMainProg node, Object data) {
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    currentScope = "main";
    return DataType.MainProg;
  }

  public Object visit(ASTArithOp node, Object data) {
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    return DataType.Plus;
  }

  public Object visit(ASTCondition node, Object data) {
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      node.jjtGetChild(i).jjtAccept(this, data);
    }
    return DataType.Condition;
  }

  public Object visit(ASTCompOp node, Object data) {
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    return DataType.Equal;
  }

  public Object visit(ASTArgList node, Object data) {
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    return DataType.ArgList;
  }

  public Object visit(ASTNum node, Object data) {
    return DataType.TypeInteger;
  }

  public Object visit(ASTBool node, Object data) {
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    return DataType.TypeBoolean;
  }

  public Object visit(ASTId node, Object data) {
    HashMap<String, HashMap<String, STC>> ST = (HashMap<String, HashMap<String, STC>>) data;
    HashMap<String, STC> innerMap = new HashMap();
    innerMap = ST.get(currentScope);
    STC mapEntry = innerMap.get(node.value);
    if (mapEntry == null) {
      if (node.value != null) {
        currentScope = node.value.toString();
        declaredFuncts.add((String) node.value);
      }
      return DataType.TypeUnknown;
    } else if (mapEntry.type == "int") {
      return DataType.TypeInteger;
    } else if (mapEntry.type == "bool") {
      return DataType.TypeBoolean;
    } else {
      return DataType.TypeUnknown;
    }
  }
}
