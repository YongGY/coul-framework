

package com.coul.common.utils.compress;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import com.coul.common.utils.FileUtils;

/**

 * @ClassName: GZipUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author linhz
 * @date 2014-8-11 下午04:55:24
 *
 */

public abstract class GZipUtils {
    
    /** 
    * @Title: gzip
    * @Description: TODO 
    * @param @param bs
    * @param @return
    * @param @throws Exception    
    * @return byte[]     
    * @throws
     */
    public static byte[] gzip(byte[] bs) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream(1000);
        GZIPOutputStream gzout = null;
        try {
            gzout = new GZIPOutputStream(bout);
            gzout.write(bs);
            gzout.flush();
        } catch (Exception e) {
            throw e;
            
        } finally {
            if (gzout != null) {
                try {
                    gzout.close();
                } catch (Exception ex) {
                }
            }
        }
        
        return bout.toByteArray();
        
    }
    
    /**
     * 
    * @Title: ungzip
    * @Description: gzip解压缩
    * @param @param bs
    * @param @return
    * @throws Exception     
    * @return byte[]     
    *  
     */
//    public static byte[] ungzip(byte[] bs) throws Exception {
//        GZIPInputStream gzin = null;
//        try {
//            ByteArrayInputStream bin = new ByteArrayInputStream(bs);
//            gzin = new GZIPInputStream(bin);
//            return FileUtils.readBytes(gzin);
//        } catch (Exception e) {
//            throw e;
//            
//        } finally {
//            //在FileUtils中会关闭gzin
//        }
//        
//    }
}
