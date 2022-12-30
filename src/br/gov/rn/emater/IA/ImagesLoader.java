/***
 * Neuroph  http://neuroph.sourceforge.net
 * Copyright by Neuroph Project (C) 2008
 *
 * This file is part of Neuroph framework.
 *
 * Neuroph is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Neuroph is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Neuroph. If not, see <http://www.gnu.org/licenses/>.
 */
package br.gov.rn.emater.IA;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import javax.imageio.ImageIO;

import org.neuroph.contrib.imgrec.FractionRgbData;
import org.neuroph.contrib.imgrec.ImageSampler;

public class ImagesLoader {

    /**
     * Organiza as imagens
     * @param imgDir
     * @param samplingResolution
     * @return
     * @throws java.io.IOException
     */
    public static Map<String, FractionRgbData> getFractionRgbDataForValues(TreeMap<String, BufferedImage> bufferedImages, Dimension samplingResolution) throws IOException {
        if (bufferedImages == null) {
            throw new IOException("Conjunto de imagens invalido");
        }

        Map<String, FractionRgbData> rgbDataMap = new HashMap<String, FractionRgbData>();


        for (Iterator iter = bufferedImages.keySet().iterator(); iter.hasNext();) {
            String filenameOfCurrentImage = (String) iter.next();
            BufferedImage img = bufferedImages.get(filenameOfCurrentImage);
            img = ImageSampler.downSampleImage(samplingResolution, img);
            StringTokenizer st = new StringTokenizer(filenameOfCurrentImage, ".");
            rgbDataMap.put(st.nextToken(), new FractionRgbData(img));
        }
        return rgbDataMap;
    }

    /**
     * Carrega uma imagem em um objeto BufferedImage 
     * @param imgFile
     * @param samplingResolution
     * @return
     * @throws java.io.IOException
     */
    public static BufferedImage loadImage(File imgFile, Dimension samplingResolution)
            throws IOException {
        BufferedImage img = ImageIO.read(imgFile);
        img = ImageSampler.downSampleImage(samplingResolution, img);
        return img;
    }
}
