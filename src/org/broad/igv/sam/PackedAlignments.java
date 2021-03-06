/*
 * Copyright (c) 2007-2013 The Broad Institute, Inc.
 * SOFTWARE COPYRIGHT NOTICE
 * This software and its documentation are the copyright of the Broad Institute, Inc. All rights are reserved.
 *
 * This software is supplied without any warranty or guaranteed support whatsoever. The Broad Institute is not responsible for its use, misuse, or functionality.
 *
 * This software is licensed under the terms of the GNU Lesser General Public License (LGPL),
 * Version 2.1 which is available at http://www.opensource.org/licenses/lgpl-2.1.php.
 */

package org.broad.igv.sam;

import org.broad.igv.feature.Range;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Convenience class for storing map from group -> list of alignments packed into rows
 * Can contain discontinuous regions
 * @author jacob
 * @date 2014-Jan-10
 */
public class PackedAlignments extends LinkedHashMap<String, List<Row>> {


    /**
     *  Set of ranges covered by this instance.
     */
    private List<? extends Range> ranges;

    PackedAlignments(List<? extends Range> ranges, Map<String, List<Row>> packedAlignments){
        super(packedAlignments);
        this.ranges = ranges;
    }

    /**
     * Calculate the total number of levels across all groups.
     * Since groups are stacked vertically, this is used to calculate height
     * @return
     */
    public int getNLevels() {
        int intervalNLevels = 0;
        for (List<Row> rows : this.values()) {
            intervalNLevels += rows.size();
        }
        return intervalNLevels;
    }

    /**
     * Whether any of the ranges contain this interval
     * @param chr
     * @param start
     * @param end
     * @return
     */
    public boolean contains(String chr, int start, int end) {
        for(Range range: this.ranges){
            if(range.contains(chr, start, end)){
                return true;
            }
        }
        return false;
    }

}
