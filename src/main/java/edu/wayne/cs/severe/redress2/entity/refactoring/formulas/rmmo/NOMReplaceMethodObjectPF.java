package edu.wayne.cs.severe.redress2.entity.refactoring.formulas.rmmo;

import edu.wayne.cs.severe.redress2.controller.metric.CodeMetric;
import edu.wayne.cs.severe.redress2.controller.metric.NOMMetric;
import edu.wayne.cs.severe.redress2.entity.TypeDeclaration;
import edu.wayne.cs.severe.redress2.entity.refactoring.RefactoringOperation;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class NOMReplaceMethodObjectPF extends ReplaceMethodObjectPredFormula {

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

        predMetrs.put(tgtCls.getQualifiedName(), 2.0);

        return predMetrs;
    }

    @Override
    public CodeMetric getMetric() {
        return new NOMMetric();
    }

}
