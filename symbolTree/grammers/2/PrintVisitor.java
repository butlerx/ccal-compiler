// Name:PrintVisitor.java
// Author: David Sinclair      Date: 29 Aug 2012
//
import java.util.*;

public class PrintVisitor implements CcalVisitor {

  private static HashMap<String, HashMap<String, STC>> ST = new HashMap<>();

  public Object visit(SimpleNode node, Object data) {
    throw new RuntimeException("Visit SimpleNode");
  }

  public Object visit(ASTProgram node, Object data) {
    HashMap<String, HashMap<String, STC>> ST = (HashMap<String, HashMap<String, STC>>) data;
    for (int i = 0; i < node.jjtGetNumChildren(); i++) {
      if(node.value != null) System.out.print(node.value + ";");
      node.jjtGetChild(i).jjtAccept(this, data);
      node.jjtGetChild(i);
    }
    return data;
  }

  public Object visit(ASTVarDecl node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return DataType.VarDeclaration;
  }

  public Object visit(ASTConstDecl node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTFunction node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTParam node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTType node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    return data;
  }

  public Object visit(ASTMainProg node, Object data) {
    System.out.print(node.value + " ");
      if(node.value != null) System.out.print(node.value + " ");
    if (node.jjtGetNumChildren() > 0) {
      node.jjtGetChild(0).jjtAccept(this, data);
    }
    return data;
  }

  public Object visit(ASTAssign node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTArithOp node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTCondition node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTCompOp node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTArgList node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTNum node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    return data;
  }

  public Object visit(ASTBool node, Object data) {
    if(node.value != null) System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    node.jjtGetChild(1).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTId node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    return data;
  }

  public Object visit(ASTVoidDec node, Object data) {
      if(node.value != null) System.out.print(node.value + " ");
    return data;
  }
}
