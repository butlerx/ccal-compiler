// Name:PrintVisitor.java
// Author: David Sinclair      Date: 29 Aug 2012
//
// Visitor for "pretty printing" an abstract syntax tree in the ExprLang language
//

public class PrintVisitor implements CcalVisitor {

  public Object visit(SimpleNode node, Object data) {
    throw new RuntimeException("Visit SimpleNode");
  }

  public Object visit(ASTProgram node, Object data) {
    node.jjtGetChild(0).jjtAccept(this, data);
    System.out.println(";");
    return (data);
  }

  public Object visit(ASTVarDecl node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTConstDecl node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTFunction node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTParam node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTType node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTMainProg node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTAssign node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTArithOp node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTCondition node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTCompOp node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTArgList node, Object data) {
    System.out.print(node.value + " ");
    node.jjtGetChild(0).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTNum node, Object data) {
    System.out.print(node.value);
    return data;
  }

  public Object visit(ASTBool node, Object data) {
    node.jjtGetChild(0).jjtAccept(this, data);
    System.out.print(" " + node.value + " ");
    node.jjtGetChild(1).jjtAccept(this, data);
    return data;
  }

  public Object visit(ASTId node, Object data) {
    System.out.print(node.value);
    return data;
  }

  public Object visit(ASTVoidDec node, Object data) {
    System.out.print(node.value);
    return data;
  }
}
