package com.example.hci_markets.presentation.ui.common

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.hci_markets.R
import com.example.hci_markets.presentation.ui.theme.HCIMarketsTheme

@Composable
fun NewsCard(
    title: String,
    location: String,
    image: Painter,
    description: String,
    url: String,
    inColumn: Boolean = true,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val newMod = remember{
        if(inColumn){
            modifier
                .fillMaxWidth()
                .padding(8.dp)
        } else {
            modifier
                .width((configuration.screenWidthDp).dp * 0.8f)
                .padding(8.dp)
        }
    }
    Card(
        modifier = newMod
            ,
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                modifier = Modifier.basicMarquee(),
                text = title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = location,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray
            )
            Text(
                url,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.Transparent.copy(alpha = 0.2f)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .clip(RoundedCornerShape(4.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    ContextCompat.startActivity(context, intent, null)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(stringResource(id = R.string.read_more))
            }
        }
    }
}

@Preview
@Composable
fun ColumnExample() {
    HCIMarketsTheme {
        Column {
            NewsCard(
                title = "My Norwich Favourites: Interview with Edd from The Bodega",
                location = "Norwich Market",
                image = painterResource(id = R.drawable.testnewsimage),
                description = "From the best wings in the city to his favourite place to grab a curry, the owner of an award-winning market stall has revealed his top five spots in the city for the My Norwich Favourites series.",
                url = "https://www.edp24.co.uk/news/24758140.norwich-favourites-interview-edd-bodega/"
            )
            NewsCard(
                title = "My Norwich Favourites: Interview with Edd from The Bodega",
                location = "Norwich Market",
                image = painterResource(id = R.drawable.testnewsimage),
                description = "From the best wings in the city to his favourite place to grab a curry, the owner of an award-winning market stall has revealed his top five spots in the city for the My Norwich Favourites series.",
                url = "https://www.edp24.co.uk/news/24758140.norwich-favourites-interview-edd-bodega/"
            )
        }
    }
}

@Preview
@Composable
fun RowExample() {
    HCIMarketsTheme {
        Row(Modifier.horizontalScroll(rememberScrollState())) {
            NewsCard(
                inColumn = false,
                title = "My Norwich Favourites: Interview with Edd from The Bodega",
                location = "Norwich Market",
                image = painterResource(id = R.drawable.testnewsimage),
                description = "From the best wings in the city to his favourite place to grab a curry, the owner of an award-winning market stall has revealed his top five spots in the city for the My Norwich Favourites series.",
                url = "https://www.edp24.co.uk/news/24758140.norwich-favourites-interview-edd-bodega/"
            )
            NewsCard(
                inColumn = false,
                title = "My Norwich Favourites: Interview with Edd from The Bodega",
                location = "Norwich Market",
                image = painterResource(id = R.drawable.testnewsimage),
                description = "From the best wings in the city to his favourite place to grab a curry, the owner of an award-winning market stall has revealed his top five spots in the city for the My Norwich Favourites series.",
                url = "https://www.edp24.co.uk/news/24758140.norwich-favourites-interview-edd-bodega/"
            )
        }
    }
}
