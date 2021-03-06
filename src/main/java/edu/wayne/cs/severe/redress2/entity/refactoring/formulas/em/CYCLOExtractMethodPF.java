package edu.wayne.cs.severe.redress2.entity.refactoring.formulas.em;

import edu.wayne.cs.severe.redress2.controller.metric.CYCLOMetric;
import edu.wayne.cs.severe.redress2.controller.metric.CodeMetric;
import edu.wayne.cs.severe.redress2.entity.TypeDeclaration;
import edu.wayne.cs.severe.redress2.entity.refactoring.RefactoringOperation;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class CYCLOExtractMethodPF extends ExtractMethodPredFormula {

    @Override
    public HashMap<String, Double> predictMetrVal(RefactoringOperation ref,
                                                  LinkedHashMap<String, LinkedHashMap<String, Double>> prevMetrics)
            throws Exception {

        TypeDeclaration srcCls = getSourceClass(ref);

        HashMap<String, Double> predMetrs = new HashMap<String, Double>();

        Double prevMetr = prevMetrics.get(srcCls.getQualifiedName()).get(
                getMetric().getMetricAcronym());
        predMetrs.put(srcCls.getQualifiedName(), prevMetr + 1);

        return predMetrs;
    }

    @Override
    public CodeMetric getMetric() {
        return new CYCLOMetric();
    }

}
