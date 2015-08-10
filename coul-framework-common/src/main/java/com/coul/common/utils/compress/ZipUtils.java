package com.coul.common.utils.compress;
//
//package com.ffcs.atte.common.utils.compress;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedOutputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.Enumeration;
//import java.util.zip.Deflater;
//import java.util.zip.Inflater;
//
//import org.apache.commons.compress.archivers.ArchiveOutputStream;
//import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
//import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
//import org.apache.commons.compress.archivers.zip.ZipFile;
//
//import com.ffcs.atte.common.log.ILogger;
//import com.ffcs.atte.common.log.LoggerFactory;
//import com.ffcs.atte.common.utils.FileUtils;
//import com.ffcs.atte.common.utils.IOUtils;
//
///**
// * 
// * 
// * @ClassName: ZipUtils
// * @Description: ZIP 和GZIP压缩解压帮助类
// * @author linhz
// * @date 2014-8-11 上午10:10:22
// * 
// */
//public abstract class ZipUtils {
//    
//    private static final ILogger log = LoggerFactory.getLogger(ZipUtils.class);
//    
//    /**
//     * /**
//     * 
//     * @Title: zip
//     * @Description: TODO(这里用一句话描述这个方法的作用)
//     * @param @param bs
//     * @param @return
//     * @param @throws Exception 设定文件
//     * @return byte[] 返回类型
//     * @throws
//     */
//    public static byte[] zip(byte[] bs) throws Exception {
//        
//        ByteArrayOutputStream o = null;
//        try {
//            o = new ByteArrayOutputStream();
//            Deflater compresser = new Deflater();
//            compresser.setInput(bs);
//            compresser.finish();
//            byte[] output = new byte[1024];
//            while (!compresser.finished()) {
//                int got = compresser.deflate(output);
//                o.write(output, 0, got);
//            }
//            o.flush();
//            return o.toByteArray();
//        } catch (Exception ex) {
//            throw ex;
//            
//        } finally {
//            if (o != null) {
//                try {
//                    o.close();
//                } catch (IOException e) {
//                    throw e;
//                }
//            }
//        }
//        
//    }
//    
//    /**
//     * 
//     * @Title: unzip
//     * @Description: TODO(这里用一句话描述这个方法的作用)
//     * @param @param bs
//     * @param @return
//     * @param @throws Exception 设定文件
//     * @return byte[] 返回类型
//     * @throws
//     */
//    public static byte[] unzip(byte[] bs) throws Exception {
//        
//        ByteArrayOutputStream o = null;
//        try {
//            o = new ByteArrayOutputStream();
//            Inflater decompresser = new Inflater();
//            decompresser.setInput(bs);
//            byte[] result = new byte[1024];
//            while (!decompresser.finished()) {
//                int resultLength = decompresser.inflate(result);
//                o.write(result, 0, resultLength);
//            }
//            decompresser.end();
//            o.flush();
//            return o.toByteArray();
//        } catch (Exception ex) {
//            throw ex;
//            
//        } finally {
//            if (o != null) {
//                try {
//                    o.close();
//                } catch (IOException e) {
//                    throw e;
//                }
//            }
//        }
//        
//    }
//    
//    /**
//     * 
//     * @param srcPathName
//     *            源文件或源目录
//     * @param destZipFile
//     *            目标压缩文件
//     * @param fileEncoding
//     *            压缩文件里的文件名的编码
//     * @throws Exception
//     */
//    
//    public static void zip(String srcPathName, String destZipFile, String fileEncoding)
//        throws Exception {
//        ZipArchiveOutputStream zos = null;
//        try {
//            final OutputStream out = new FileOutputStream(destZipFile);
//            zos = new ZipArchiveOutputStream(out);
//            zos.setEncoding(fileEncoding);
//            
//            RecursiveZip(new File(srcPathName), "", zos);
//            
//        } catch (Exception e) {
//            log.error("", e);
//        } finally {
//            try {
//                if (zos != null) {
//                    zos.close();
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        
//    }
//    
//    // 递归压缩函数
//    // fDir　要压缩的目录或者文件
//    // pName　父压缩记录名称，第一次调用应该被设置为一个空字符串""
//    // zos　压缩输出流
//    /**
//     * 
//     */
//    private static void RecursiveZip(File fDir, String pName, ArchiveOutputStream zos)
//        throws Exception {
//        if (fDir.isDirectory()) {
//            // 如果为目录，ZipEntry名称的尾部应该以反斜杠"/"结尾
//            
//            zos.putArchiveEntry(new ZipArchiveEntry(pName + fDir.getName() + "/"));
//            File[] files = fDir.listFiles();
//            if (files != null) {
//                for (int i = 0; i < files.length; i++) {
//                    // 进行递归，同时传递父文件ZipEntry的名称，还有压缩输出流
//                    RecursiveZip(files[i], pName + fDir.getName() + "/", zos);
//                }
//            }
//        }
//        if (fDir.isFile()) {
//            // byte[] bt = new byte[2048];
//            ZipArchiveEntry ze = new ZipArchiveEntry(pName + fDir.getName());
//            // 设置压缩前的文件大小
//            ze.setSize(fDir.length());
//            zos.putArchiveEntry(ze);
//            FileInputStream fis = new FileInputStream(fDir);
//            // int i = 0;
//            // while ((i = fis.read(bt)) != -1) {
//            // zos.write(bt, 0, i);
//            // }
//            // fis.close();
//            try {
//                IOUtils.copy(fis, zos, false);
//            } finally {
//                fis.close();
//            }
//            zos.closeArchiveEntry();
//        }
//    }
//    
//    /**
//     * 解压zip文件到指定的目录 File Unzip
//     * 
//     * @param sToPath
//     *            Directory path to be unzipted to
//     * @param sZipFile
//     *            zip File Name to be ziped
//     */
//    public static void unZip(String sToPath, String sZipFile, String fileNameEncoding)
//        throws Exception {
//        
//        if (null == sToPath || ("").equals(sToPath.trim())) {
//            File objZipFile = new File(sZipFile);
//            sToPath = objZipFile.getParent();
//            // System.out.println(sToPath);
//        }
//        ZipFile zfile = new ZipFile(sZipFile, fileNameEncoding);
//        
//        Enumeration<?> zList = zfile.getEntries();
//        ZipArchiveEntry ze = null;
//        byte[] buf = new byte[1024];
//        while (zList.hasMoreElements()) {
//            
//            ze = (ZipArchiveEntry) zList.nextElement();
//            
//            if (ze.isDirectory()) {
//                // System.out.println(ze.getName());
//                String dir = FileUtils.getDirectoryPath(sToPath + "/" + ze.getName());
//                // System.out.println(dir);
//                FileUtils.makeDirectory(dir);
//                // System.out.println("1111");
//                
//            } else {
//                // System.out.println("2222");
//                // System.out.println(ze.getName());
//                String filePath = FileUtils.getDirectoryPath(sToPath + "/" + ze.getName());
//                // zfile.getInputStream(ze);
//                // FileUtils.write(pathName, bs);
//                
//                OutputStream os = new BufferedOutputStream(new FileOutputStream(filePath));
//                InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
//                int readLen = 0;
//                while ((readLen = is.read(buf, 0, 1024)) != -1) {
//                    os.write(buf, 0, readLen);
//                }
//                is.close();
//                os.close();
//            }
//        }
//        zfile.close();
//    }
//    
//    /**
//     * 
//     * @Title: unZip
//     * @Description: TODO(这里用一句话描述这个方法的作用)
//     * @param @param sToPath
//     * @param @param sZipFile
//     * @param @param fileNameEncoding
//     * @param @throws Exception 设定文件
//     * @return void 返回类型
//     * @throws
//     */
//    public static void unZip(String sToPath, File sZipFile, String fileNameEncoding)
//        throws Exception {
//        
//        if (null == sToPath || ("").equals(sToPath.trim())) {
//            File objZipFile = sZipFile;
//            sToPath = objZipFile.getParent();
//            // System.out.println(sToPath);
//        }
//        ZipFile zfile = new ZipFile(sZipFile, fileNameEncoding);
//        
//        Enumeration<?> zList = zfile.getEntries();
//        ZipArchiveEntry ze = null;
//        byte[] buf = new byte[1024];
//        while (zList.hasMoreElements()) {
//            
//            ze = (ZipArchiveEntry) zList.nextElement();
//            
//            if (ze.isDirectory()) {
//                // System.out.println(ze.getName());
//                String dir = FileUtils.getDirectoryPath(sToPath + "/" + ze.getName());
//                // System.out.println(dir);
//                FileUtils.makeDirectory(dir);
//                // System.out.println("1111");
//                
//            } else {
//                // System.out.println("2222");
//                // System.out.println(ze.getName());
//                String filePath = FileUtils.getDirectoryPath(sToPath + "/" + ze.getName());
//                // zfile.getInputStream(ze);
//                // FileUtils.write(pathName, bs);
//                
//                OutputStream os = new BufferedOutputStream(new FileOutputStream(filePath));
//                InputStream is = new BufferedInputStream(zfile.getInputStream(ze));
//                int readLen = 0;
//                while ((readLen = is.read(buf, 0, 1024)) != -1) {
//                    os.write(buf, 0, readLen);
//                }
//                is.close();
//                os.close();
//            }
//        }
//        zfile.close();
//    }
//    
//    public static void main(String[] args) throws Exception {
//        // byte[] old="中国你好aaa".getBytes("utf-8");
//        // //System.out.println(HexUtils.format(old));
//        // byte[] bs =gzip(old);
//        // System.out.println(HexUtils.format(bs));
//        // FileUtils.write("e:/ok.gzip", bs);
//        // byte[] outbys=FileUtils.readBytes("e:/ok.gzip");
//        // byte[] un=ungzip(outbys);
//        // System.out.println(HexUtils.format(un));
//        // System.out.println(new String(un,"utf-8"));
//        //
//        // System.out.println("------------------");
//        
//        // ZipUtils.releaseZip("f:/tttt", "f:/222.zip");
//        
//        // byte[] old = "中国你好aaa".getBytes("utf-8");
//        // // System.out.println(HexUtils.format(old));
//        // byte[] news = zip(old);
//        // System.out.println(ByteUtils.format(news));
//        // String tt = new String(news, "utf-8");
//        // // System.out.println(tt);
//        // System.out.println(ByteUtils.format(tt.getBytes("utf-8")));
//        //
//        // FileUtils.write("c:/test.txt", tt, "utf-8");
//        // news = FileUtils.readTxt(new File("c:/test.txt"), "utf-8").getBytes(
//        // "utf-8");
//        // byte[] ss = unzip(news);
//        // System.out.println(ByteUtils.format(news));
//        // System.out.println(new String(ss, "utf-8"));
//        
//        // Deflater
//        // ZipUtils.zip(fileNames, outZipFileName, fileNameEncoding)
//        // ZipUtils.unZip("d:/sunchaojin", "d:/6253+240x320+精品.zip", "gbk");
//        // ZipUtils.unZip("d:/eekk", "d:/ee44.zip", "gbk");
//        
//    }
//}
