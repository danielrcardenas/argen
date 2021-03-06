package edu.wayne.cs.severe.redress2.entity.refactoring.formulas.rdi;

import edu.wayne.cs.severe.redress2.controller.metric.CodeMetric;
import edu.wayne.cs.severe.redress2.controller.metric.NOCMetric;
import edu.wayne.cs.severe.redress2.entity.TypeDeclaration;
import edu.wayne.cs.severe.redress2.entity.refactoring.RefactoringOperation;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class NOCReplaceDelegInhPF extends ReplaceDelegInherPredFormula {

    @Override
    public HashMap<String, Double> predictMetrVal(RefactoringOperation ref,
                                                  LinkedHashMap<String, LinkedHashMap<String, Double>> prevMetrics)
            throws Exception {

        TypeDeclaration srcCls = getSourceClass(ref);
        TypeDeclaration tgtCls = getTargetClass(ref);

        HashMap<String, Double> predMetrs = new HashMap<String, Double>();

        Double prevMetr = prevMetrics.get(srcCls.getQualifiedName()).get(
                getMetric().getMetricAcronym());
        predMetrs.put(srcCls.getQualifiedName(), prevMetr);

        LinkedHashMap<String, Double> prevMetrsTgt = prevMetrics.get(tgtCls
                .getQualifiedName());
        Double prevMetrTgt = prevMetrsTgt == null ? 0.0 : prevMetrsTgt
                .get(getMetric().getMetricAcronym());
        predMetrs.put(tgtCls.getQualifiedName(), prevMetrTgt + 1);

        return predMetrs;
    }

    @Override
    public CodeMetric getMetric() {
        return new NOCMetric();
    }

}
