// Generated from Boa.g by ANTLR 4.5
package boa.parser;

import java.util.ArrayList;

import boa.compiler.ast.*;
import boa.compiler.ast.expressions.*;
import boa.compiler.ast.literals.*;
import boa.compiler.ast.statements.*;
import boa.compiler.ast.types.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link BoaParser}.
 */
public interface BoaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link BoaParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(BoaParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(BoaParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(BoaParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(BoaParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#programStatement}.
	 * @param ctx the parse tree
	 */
	void enterProgramStatement(BoaParser.ProgramStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#programStatement}.
	 * @param ctx the parse tree
	 */
	void exitProgramStatement(BoaParser.ProgramStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(BoaParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(BoaParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterTypeDeclaration(BoaParser.TypeDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#typeDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitTypeDeclaration(BoaParser.TypeDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#staticVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStaticVariableDeclaration(BoaParser.StaticVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#staticVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStaticVariableDeclaration(BoaParser.StaticVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVariableDeclaration(BoaParser.VariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#variableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVariableDeclaration(BoaParser.VariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(BoaParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(BoaParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#component}.
	 * @param ctx the parse tree
	 */
	void enterComponent(BoaParser.ComponentContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#component}.
	 * @param ctx the parse tree
	 */
	void exitComponent(BoaParser.ComponentContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#enumBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterEnumBodyDeclaration(BoaParser.EnumBodyDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#enumBodyDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitEnumBodyDeclaration(BoaParser.EnumBodyDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void enterArrayType(BoaParser.ArrayTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#arrayType}.
	 * @param ctx the parse tree
	 */
	void exitArrayType(BoaParser.ArrayTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#tupleType}.
	 * @param ctx the parse tree
	 */
	void enterTupleType(BoaParser.TupleTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#tupleType}.
	 * @param ctx the parse tree
	 */
	void exitTupleType(BoaParser.TupleTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#enumType}.
	 * @param ctx the parse tree
	 */
	void enterEnumType(BoaParser.EnumTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#enumType}.
	 * @param ctx the parse tree
	 */
	void exitEnumType(BoaParser.EnumTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#member}.
	 * @param ctx the parse tree
	 */
	void enterMember(BoaParser.MemberContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#member}.
	 * @param ctx the parse tree
	 */
	void exitMember(BoaParser.MemberContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#mapType}.
	 * @param ctx the parse tree
	 */
	void enterMapType(BoaParser.MapTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#mapType}.
	 * @param ctx the parse tree
	 */
	void exitMapType(BoaParser.MapTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#stackType}.
	 * @param ctx the parse tree
	 */
	void enterStackType(BoaParser.StackTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#stackType}.
	 * @param ctx the parse tree
	 */
	void exitStackType(BoaParser.StackTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#setType}.
	 * @param ctx the parse tree
	 */
	void enterSetType(BoaParser.SetTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#setType}.
	 * @param ctx the parse tree
	 */
	void exitSetType(BoaParser.SetTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#outputType}.
	 * @param ctx the parse tree
	 */
	void enterOutputType(BoaParser.OutputTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#outputType}.
	 * @param ctx the parse tree
	 */
	void exitOutputType(BoaParser.OutputTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#modelType}.
	 * @param ctx the parse tree
	 */
	void enterModelType(BoaParser.ModelTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#modelType}.
	 * @param ctx the parse tree
	 */
	void exitModelType(BoaParser.ModelTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#functionType}.
	 * @param ctx the parse tree
	 */
	void enterFunctionType(BoaParser.FunctionTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#functionType}.
	 * @param ctx the parse tree
	 */
	void exitFunctionType(BoaParser.FunctionTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#visitorType}.
	 * @param ctx the parse tree
	 */
	void enterVisitorType(BoaParser.VisitorTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#visitorType}.
	 * @param ctx the parse tree
	 */
	void exitVisitorType(BoaParser.VisitorTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(BoaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(BoaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmptyStatement(BoaParser.EmptyStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#emptyStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmptyStatement(BoaParser.EmptyStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentStatement(BoaParser.AssignmentStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#assignmentStatement}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentStatement(BoaParser.AssignmentStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(BoaParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(BoaParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void enterBreakStatement(BoaParser.BreakStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#breakStatement}.
	 * @param ctx the parse tree
	 */
	void exitBreakStatement(BoaParser.BreakStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void enterContinueStatement(BoaParser.ContinueStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#continueStatement}.
	 * @param ctx the parse tree
	 */
	void exitContinueStatement(BoaParser.ContinueStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#doStatement}.
	 * @param ctx the parse tree
	 */
	void enterDoStatement(BoaParser.DoStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#doStatement}.
	 * @param ctx the parse tree
	 */
	void exitDoStatement(BoaParser.DoStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#emitStatement}.
	 * @param ctx the parse tree
	 */
	void enterEmitStatement(BoaParser.EmitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#emitStatement}.
	 * @param ctx the parse tree
	 */
	void exitEmitStatement(BoaParser.EmitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void enterForStatement(BoaParser.ForStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#forStatement}.
	 * @param ctx the parse tree
	 */
	void exitForStatement(BoaParser.ForStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#forExpression}.
	 * @param ctx the parse tree
	 */
	void enterForExpression(BoaParser.ForExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#forExpression}.
	 * @param ctx the parse tree
	 */
	void exitForExpression(BoaParser.ForExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#forVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterForVariableDeclaration(BoaParser.ForVariableDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#forVariableDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitForVariableDeclaration(BoaParser.ForVariableDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#forExpressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterForExpressionStatement(BoaParser.ForExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#forExpressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitForExpressionStatement(BoaParser.ForExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void enterExpressionStatement(BoaParser.ExpressionStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#expressionStatement}.
	 * @param ctx the parse tree
	 */
	void exitExpressionStatement(BoaParser.ExpressionStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(BoaParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(BoaParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(BoaParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(BoaParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void enterSwitchStatement(BoaParser.SwitchStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#switchStatement}.
	 * @param ctx the parse tree
	 */
	void exitSwitchStatement(BoaParser.SwitchStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#switchCase}.
	 * @param ctx the parse tree
	 */
	void enterSwitchCase(BoaParser.SwitchCaseContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#switchCase}.
	 * @param ctx the parse tree
	 */
	void exitSwitchCase(BoaParser.SwitchCaseContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#foreachStatement}.
	 * @param ctx the parse tree
	 */
	void enterForeachStatement(BoaParser.ForeachStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#foreachStatement}.
	 * @param ctx the parse tree
	 */
	void exitForeachStatement(BoaParser.ForeachStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#existsStatement}.
	 * @param ctx the parse tree
	 */
	void enterExistsStatement(BoaParser.ExistsStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#existsStatement}.
	 * @param ctx the parse tree
	 */
	void exitExistsStatement(BoaParser.ExistsStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#ifallStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfallStatement(BoaParser.IfallStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#ifallStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfallStatement(BoaParser.IfallStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void enterWhileStatement(BoaParser.WhileStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#whileStatement}.
	 * @param ctx the parse tree
	 */
	void exitWhileStatement(BoaParser.WhileStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#visitStatement}.
	 * @param ctx the parse tree
	 */
	void enterVisitStatement(BoaParser.VisitStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#visitStatement}.
	 * @param ctx the parse tree
	 */
	void exitVisitStatement(BoaParser.VisitStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#stopStatement}.
	 * @param ctx the parse tree
	 */
	void enterStopStatement(BoaParser.StopStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#stopStatement}.
	 * @param ctx the parse tree
	 */
	void exitStopStatement(BoaParser.StopStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(BoaParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(BoaParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(BoaParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(BoaParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void enterConjunction(BoaParser.ConjunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#conjunction}.
	 * @param ctx the parse tree
	 */
	void exitConjunction(BoaParser.ConjunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#comparison}.
	 * @param ctx the parse tree
	 */
	void enterComparison(BoaParser.ComparisonContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#comparison}.
	 * @param ctx the parse tree
	 */
	void exitComparison(BoaParser.ComparisonContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void enterSimpleExpression(BoaParser.SimpleExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#simpleExpression}.
	 * @param ctx the parse tree
	 */
	void exitSimpleExpression(BoaParser.SimpleExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#term}.
	 * @param ctx the parse tree
	 */
	void enterTerm(BoaParser.TermContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#term}.
	 * @param ctx the parse tree
	 */
	void exitTerm(BoaParser.TermContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#factor}.
	 * @param ctx the parse tree
	 */
	void enterFactor(BoaParser.FactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#factor}.
	 * @param ctx the parse tree
	 */
	void exitFactor(BoaParser.FactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#selector}.
	 * @param ctx the parse tree
	 */
	void enterSelector(BoaParser.SelectorContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#selector}.
	 * @param ctx the parse tree
	 */
	void exitSelector(BoaParser.SelectorContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(BoaParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(BoaParser.IndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#call}.
	 * @param ctx the parse tree
	 */
	void enterCall(BoaParser.CallContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#call}.
	 * @param ctx the parse tree
	 */
	void exitCall(BoaParser.CallContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#operand}.
	 * @param ctx the parse tree
	 */
	void enterOperand(BoaParser.OperandContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#operand}.
	 * @param ctx the parse tree
	 */
	void exitOperand(BoaParser.OperandContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#unaryFactor}.
	 * @param ctx the parse tree
	 */
	void enterUnaryFactor(BoaParser.UnaryFactorContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#unaryFactor}.
	 * @param ctx the parse tree
	 */
	void exitUnaryFactor(BoaParser.UnaryFactorContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#parenExpression}.
	 * @param ctx the parse tree
	 */
	void enterParenExpression(BoaParser.ParenExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#parenExpression}.
	 * @param ctx the parse tree
	 */
	void exitParenExpression(BoaParser.ParenExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#functionExpression}.
	 * @param ctx the parse tree
	 */
	void enterFunctionExpression(BoaParser.FunctionExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#functionExpression}.
	 * @param ctx the parse tree
	 */
	void exitFunctionExpression(BoaParser.FunctionExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#visitorExpression}.
	 * @param ctx the parse tree
	 */
	void enterVisitorExpression(BoaParser.VisitorExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#visitorExpression}.
	 * @param ctx the parse tree
	 */
	void exitVisitorExpression(BoaParser.VisitorExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#composite}.
	 * @param ctx the parse tree
	 */
	void enterComposite(BoaParser.CompositeContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#composite}.
	 * @param ctx the parse tree
	 */
	void exitComposite(BoaParser.CompositeContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(BoaParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(BoaParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void enterIdentifier(BoaParser.IdentifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#identifier}.
	 * @param ctx the parse tree
	 */
	void exitIdentifier(BoaParser.IdentifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void enterIntegerLiteral(BoaParser.IntegerLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#integerLiteral}.
	 * @param ctx the parse tree
	 */
	void exitIntegerLiteral(BoaParser.IntegerLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#floatingPointLiteral}.
	 * @param ctx the parse tree
	 */
	void enterFloatingPointLiteral(BoaParser.FloatingPointLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#floatingPointLiteral}.
	 * @param ctx the parse tree
	 */
	void exitFloatingPointLiteral(BoaParser.FloatingPointLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#characterLiteral}.
	 * @param ctx the parse tree
	 */
	void enterCharacterLiteral(BoaParser.CharacterLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#characterLiteral}.
	 * @param ctx the parse tree
	 */
	void exitCharacterLiteral(BoaParser.CharacterLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void enterStringLiteral(BoaParser.StringLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#stringLiteral}.
	 * @param ctx the parse tree
	 */
	void exitStringLiteral(BoaParser.StringLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link BoaParser#timeLiteral}.
	 * @param ctx the parse tree
	 */
	void enterTimeLiteral(BoaParser.TimeLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link BoaParser#timeLiteral}.
	 * @param ctx the parse tree
	 */
	void exitTimeLiteral(BoaParser.TimeLiteralContext ctx);
}