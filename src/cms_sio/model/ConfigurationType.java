/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

/**
 *
 * @author sgoyet
 */
public enum ConfigurationType {
    TEXT {
      public String toString() {
          return "TEXT";
      }
    },
      HTMLFragment {
      public String toString() {
          return "HTMLFragment";
      }
    },
    Image {
      public String toString() {
          return "Image";
      }
    },
    Video {
      public String toString() {
          return "Video";
      }
    },
        Music {
      public String toString() {
          return "Music";
      }
    },
                Title {
      public String toString() {
          return "Title";
      }
    },
                                PageRef {
      public String toString() {
          return "PageRef";
      }
    },
                                    DataUrl {
      public String toString() {
          return "DataUrl";
      }
    }                       
}
