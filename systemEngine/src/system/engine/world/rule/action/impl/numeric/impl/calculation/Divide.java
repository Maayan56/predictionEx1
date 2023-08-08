package system.engine.world.rule.action.impl.numeric.impl.calculation;

import system.engine.world.execution.instance.property.api.PropertyInstance;
import system.engine.world.definition.entity.api.EntityDefinition;
import system.engine.world.rule.action.expression.api.Expression;
import system.engine.world.rule.context.Context;
import system.engine.world.rule.enums.Type;

import static system.engine.world.creation.expression.ExpressionCreation.craeteExpression;

public class Divide extends Calculation {
    public Divide(EntityDefinition entityDefinitionParam, String propertyNameParam, String expressionStrParam1, String expressionStrParam2){
        super(entityDefinitionParam, propertyNameParam, expressionStrParam1, expressionStrParam2);
    }

    @Override
    public void executeAction(Context context) {
        PropertyInstance propertyInstance = entityInstance.getPropertyByName(resultPropName);
        Expression expression1 = craeteExpression(expressionStrArg1, entityInstance, resultPropName);
        Expression expression2 = craeteExpression(expressionStrArg2, entityInstance, resultPropName);
        Type type = propertyInstance.getPropertyDefinition().getType();

        if (!verifyNumericPropertyType(propertyInstance) | (!verifyNumericExpressionValue(expression1)) | (!verifyNumericExpressionValue(expression2))) {
            throw new IllegalArgumentException("increase action can't operate on a none number property [" + resultPropName);
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

    @Override
    public boolean verifyNumericPropertyType(PropertyInstance propertyValue) {
        return verifyNumericPropertyType(propertyValue);
    }

    @Override
    public boolean verifyNumericExpressionValue(Expression expression) {
        return verifyNumericExpressionValue(expression);
    }
}
