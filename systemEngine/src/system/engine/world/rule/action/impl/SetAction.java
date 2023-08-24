package system.engine.world.rule.action.impl;

import system.engine.world.creation.api.ExpressionCreation;
import system.engine.world.creation.impl.expression.ExpressionCreationImpl;
import system.engine.world.definition.entity.api.EntityDefinition;
import system.engine.world.execution.instance.property.api.PropertyInstance;
import system.engine.world.rule.action.api.AbstractAction;
import system.engine.world.rule.action.api.ActionType;
import system.engine.world.rule.action.expression.api.Expression;
import system.engine.world.rule.context.Context;
import system.engine.world.rule.enums.Type;

public class SetAction extends AbstractAction {
        private final String propertyName;
        private final String expressionStr;

    public SetAction(EntityDefinition entityDefinitionParam, String propertyNameParam, String expressionStrParam) {
        super(ActionType.INCREASE, entityDefinitionParam);
        propertyName = propertyNameParam;
        expressionStr =expressionStrParam;
    }

        @Override
        public void executeAction(Context context) throws IllegalArgumentException{
            ExpressionCreation expressionCreation = new ExpressionCreationImpl();
            PropertyInstance propertyInstance = context.getPrimaryEntityInstance().getPropertyByName(propertyName);
            Expression expression = expressionCreation.craeteExpression(expressionStr, context.getPrimaryEntityInstance(), propertyName);
            Object expressionVal=  expression.evaluateExpression(context);
            Type propertyType = propertyInstance.getPropertyDefinition().getType();

            if (!verifySuitableType(propertyType, expressionVal)) {
                throw new IllegalArgumentException("set action can't operate with expression type different from type of property " + propertyName);
            }

            setPropertyValue(propertyInstance, expressionVal);


        }

        private void setPropertyValue(PropertyInstance propertyInstance, Object expressionVal){
            Type propertyType = propertyInstance.getPropertyDefinition().getType();

            if(propertyInstance.getPropertyDefinition().doesHaveRange()) {
                switch (propertyType) {
                    case DECIMAL:
                        Integer iMinRange = (Integer) propertyInstance.getPropertyDefinition().getRange().get(0);
                        Integer iMaxRange = (Integer) propertyInstance.getPropertyDefinition().getRange().get(1);
                        if ((Integer) expressionVal > iMaxRange) {
                            expressionVal = iMaxRange;
                        } else if ((Integer) expressionVal < iMinRange) {
                            expressionVal = iMinRange;
                        }

                        break;
                    case FLOAT:
                        Float fMinRange = (Float) propertyInstance.getPropertyDefinition().getRange().get(0);
                        Float fMaxRange = (Float) propertyInstance.getPropertyDefinition().getRange().get(1);
                        if ((Float) expressionVal > fMaxRange) {
                            expressionVal = fMaxRange;
                        } else if ((Float) expressionVal < fMinRange) {
                            expressionVal = fMinRange;
                        }
                        break;
                }
            }

            propertyInstance.setValue(expressionVal);
            }

    private boolean verifySuitableType(Type propertyType, Object expressionVal) {
        boolean result = false;

        switch (propertyType) {
            case DECIMAL:
                result= (expressionVal instanceof Integer);
                break;
            case FLOAT:
                result= (expressionVal instanceof Float | expressionVal instanceof Integer);
                break;
            case BOOLEAN:
                result= (expressionVal instanceof Boolean | expressionVal instanceof String);
                break;
            case STRING:
                result= (expressionVal instanceof String);
                break;
        }
        return result;
    }
}
