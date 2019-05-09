/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2011-2019 Broad Institute, Aiden Lab
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package juicebox.data;

public class NVIRetriever {
    public static long getNormVectorIndex() {

        long normVectorIndex = 0;

        // If nvi is not supplied, try reading from remote lambda service
        if (!this.config.nvi && this.remote && this.path) {
            const url = new URL(this.path)
            const key = encodeURIComponent(url.hostname + url.pathname)
            const nviResponse = await fetch('https://t5dvc6kn3f.execute-api.us-east-1.amazonaws.com/dev/nvi/' + key)
            if (nviResponse.status == = 200) {
                const nvi = await nviResponse.text()
                if (nvi) {
                    this.config.nvi = nvi
                }
            }
        }

        if (this.config.nvi) {
            const nviArray = decodeURIComponent(this.config.nvi).split(",")
            const range = {start:parseInt(nviArray[0]), size:parseInt(nviArray[1])}
            return this.readNormVectorIndex(range)
        } else {
            try {
                await this.readNormExpectedValuesAndNormVectorIndex()
                return this.normVectorIndex
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return normVectorIndex;
    }
}
