package system.engine.world.rule.action.impl.numeric.impl.calculation;

import system.engine.world.rule.action.impl.numeric.api.NumericVerify;
import system.engine.world.rule.context.Context;
import system.engine.world.rule.enums.Type;
import system.engine.world.execution.instance.property.api.PropertyInstance;
import system.engine.world.definition.entity.api.EntityDefinition;
import system.engine.world.rule.action.expression.api.Expression;

import static system.engine.world.creation.impl.expression.ExpressionCreationImpl.craeteExpression;

public class DivideAction extends CalculationAction {
    public DivideAction(EntityDefinition entityDefinitionParam, String propertyNameParam, String expressionStrParam1, String expressionStrParam2){
        super(entityDefinitionParam, propertyNameParam, expressionStrParam1, expressionStrParam2);
    }

    @Override
    public void executeAction(Context context) {
        PropertyInstance propertyInstance = context.getPrimaryEntityInstance().getPropertyByName(resultPropName);
        Expression expression1 = craeteExpression(expressionStrArg1, context.getPrimaryEntityInstance(), resultPropName);
        Expression expression2 = craeteExpression(expressionStrArg2, context.getPrimaryEntityInstance(), resultPropName);
        Type type = propertyInstance.getPropertyDefinition().getType();

        if (!NumericVerify.verifyNumericPropertyType(propertyInstance)){
            throw new IllegalArgumentException("divide action can't operate on a none number property [" + resultPropName);
        }
        if (!NumericVerify.verifyNumericExpressionValue(expression1, context) |
        (!NumericVerify.verifyNumericExpressionValue(expression2, context)) |
                !NumericVerify.verifySuitableType(type,expression1, context) |
                !NumericVerify.verifySuitableType(type,expression2, context)) {
            throw new IllegalArgumentException("can't cast one of expression value to type of property [" + resultPropName);
        }

        switch (type) {
            case DECIMAL:
                Integer i1 = (Integer)(expression1.evaluateExpression(context));
                Integer i2 = (Integer)(expression2.evaluateExpression(context));
                propertyInstance.setValue(i1 / i2);
                break;
            case FLOAT:
                Float f1 = (Float)(expression1.evaluateExpression(context));
                Float f2 = (Float)(expression2.evaluateExpression(context));
                propertyInstance.setValue(f1 / f2);
                break;
        }
    }

}

