package edu.wayne.cs.severe.redress2.entity.refactoring.formulas.im;

import edu.wayne.cs.severe.redress2.controller.MetricUtils;
import edu.wayne.cs.severe.redress2.controller.metric.CodeMetric;
import edu.wayne.cs.severe.redress2.controller.metric.LCOM2Metric;
import edu.wayne.cs.severe.redress2.entity.TypeDeclaration;
import edu.wayne.cs.severe.redress2.entity.refactoring.RefactoringOperation;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class LCOM2InlineMethodPF extends InlineMethodPredFormua {

    @Override
    public HashMap<String, Double> predictMetrVal(RefactoringOperation ref,
                                                  LinkedHashMap<String, LinkedHashMap<String, Double>> prevMetrics)
            throws Exception {

        TypeDeclaration srcCls = getSourceClass(ref);

        HashMap<String, Double> predMetrs = new HashMap<String, Double>();

        predMetrs.put(srcCls.getQualifiedName(), getValLCOM5(srcCls));

        return predMetrs;
    }

    private Double getValLCOM5(TypeDeclaration typeDcl) throws Exception {

        if (typeDcl.getCompUnit() == null) {
            return 0.0;
        }

        File compUnitFile = typeDcl.getCompUnit().getSrcFile();
        HashSet<String> fields = MetricUtils.getFields(typeDcl);

        double numFieldUsage = 0.0;
        for (String field : fields) {
            int numField = MetricUtils.getNumberOfMethodsUsingString(typeDcl,
                    field);
            numFieldUsage += numField;
        }

        double numFields = fields.size();
        Double numMethods = MetricUtils.getNumberOfMethods(typeDcl,
                compUnitFile) - 1;

        double metric = (numMethods == 0 || numFields == 0) ? 0
                : 1 - ((numFieldUsage) / (numMethods * numFields));

        return metric;
    }

    @Override
    public CodeMetric getMetric() {
        return new LCOM2Metric();
    }

}
