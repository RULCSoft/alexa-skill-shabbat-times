/**
 * Copyright Tomer Figenblat
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.tomfi.alexa.skills.shabbattimes.api.response;

import com.google.api.client.util.Key;
import info.tomfi.alexa.skills.shabbattimes.api.response.items.ResponseItem;
import info.tomfi.alexa.skills.shabbattimes.api.response.items.ResponseLocation;
import java.util.List;

/**
 * Pojo for consuming json response from the api.
 *
 * @author Tomer Figenblat {@literal <tomer.figenblat@gmail.com>}
 */
public final class ApiResponse {
  @Key private String date;
  @Key private List<ResponseItem> items;
  @Key private String link;
  @Key private ResponseLocation location;
  @Key private String title;

  public ApiResponse() {
    //
  }

  public String getDate() {
    return date;
  }

  public List<ResponseItem> getItems() {
    return items;
  }

  public String getLink() {
    return link;
  }

  public ResponseLocation getLocation() {
    return location;
  }

  public String getTitle() {
    return title;
  }
}
